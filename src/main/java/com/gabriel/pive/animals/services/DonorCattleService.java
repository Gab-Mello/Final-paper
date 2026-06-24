package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.*;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.InvalidDateException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.embryoproduction.entities.Embryo;
import com.gabriel.pive.fiv.embryoproduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.embryoproduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocytecollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DonorCattleService {

    private final DonorCattleRepository donorCattleRepository;
    private final EmbryoRepository embryoRepository;
    private final OocyteCollectionRepository oocyteCollectionRepository;
    private final FivRepository fivRepository;

    @Transactional
    public DonorCattleDto create(DonorCattleDto dto){
        if (donorCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma doadora com este número de registro já existe.");
        }

        if (dto.birth().isAfter(LocalDate.now())){
            throw new InvalidDateException();
        }

        DonorCattle donorCattle = donorCattleRepository.save(dto.toDonorCattle());
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public List<DonorCattleDto> findAll(){
        List<DonorCattle> list = donorCattleRepository.findAll();
        return DonorCattleDto.toDonorCattleDtoList(list);
    }

    public List<DonorCattleDto> getAvailableDonorsInFiv(Long fivId){
        fivRepository.findById(fivId).orElseThrow(FivNotFoundException::new);
        return DonorCattleDto.toDonorCattleDtoList(donorCattleRepository.findDonorsNotUsedInFiv(fivId));
    }

    public List<DonorCattleDto> getDonorsWithHighestOocytesCollected() {
        List<DonorCattle> donors = donorCattleRepository.findDonorsWithHighestOocytesCollected();
        return DonorCattleDto.toDonorCattleDtoList(donors);
    }

    public List<DonorCattleDto> getDonorsWithHighestEmbryoPercentage() {
        List<DonorCattle> donors = donorCattleRepository.findDonorsWithHighestEmbryoPercentage();
        return DonorCattleDto.toDonorCattleDtoList(donors);
    }

    @Transactional
    public void updateAverageViableOocytes(DonorCattle donorCattle) {
        List<OocyteCollection> oocyteCollections = donorCattle.getOocyteCollections();

        double totalViableOocytes = oocyteCollections.stream()
                .mapToInt(OocyteCollection::getViableOocytes)
                .sum();
        donorCattle.setAverageViableOocytes(totalViableOocytes/oocyteCollections.size());

        donorCattleRepository.save(donorCattle);
    }

    @Transactional
    public void updateAverageViableEmbryos(DonorCattle donorCattle) {
        List<OocyteCollection> oocyteCollections = donorCattle.getOocyteCollections();

        double totalEmbryosPercent = 0;
        int collectionCount = 0;

        for (OocyteCollection collection : oocyteCollections) {
            if (collection.getEmbryoProduction() != null) {
                totalEmbryosPercent += collection.getEmbryoProduction().getEmbryosPercentage();
                collectionCount++;
            }
        }

        if (collectionCount > 0) {
            double averageEmbryosPercentage = totalEmbryosPercent / collectionCount;
            donorCattle.setAverageEmbryoPercentage(averageEmbryosPercentage);
        } else {
            donorCattle.setAverageEmbryoPercentage(0.0);
        }
        donorCattleRepository.save(donorCattle);
    }

    private Double formatDoubleToTwoDecimals(Double value) {
        if (value == null) return null;
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public DonorCattleDto findById(Long id){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                .orElseThrow(DonorCattleNotFoundException::new);
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public List<DonorCattleDto> findByRegistrationNumber(String registrationNumber){
        return DonorCattleDto.toDonorCattleDtoList(donorCattleRepository.
                findByRegistrationNumberStartingWith(registrationNumber.toUpperCase()));
    }

    @Transactional
    public void delete(Long id){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                        .orElseThrow(DonorCattleNotFoundException::new);

        List<Embryo> embryos = embryoRepository.findAllByEmbryoDonorCattle(donorCattle);
        for (Embryo embryo : embryos){
            embryo.setEmbryoDonorCattle(null);
            embryoRepository.save(embryo);
        }

        List<OocyteCollection> oocyteCollections = oocyteCollectionRepository.findAllByDonorCattle(donorCattle);
        for (OocyteCollection collection : oocyteCollections) {
            collection.setDonorCattle(null);
            oocyteCollectionRepository.save(collection);
        }

        donorCattleRepository.deleteById(id);
    }

    @Transactional
    public DonorCattleDto edit(Long id, DonorCattleDto dto){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                .orElseThrow(DonorCattleNotFoundException::new);

        if (donorCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != donorCattle
            && donorCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma doadora com este número de registro já existe.");
        }

        if (dto.birth().isAfter(LocalDate.now())){
            throw new InvalidDateException();
        }

        donorCattle.setName(dto.name());
        donorCattle.setBreed(dto.breed());
        donorCattle.setBirth(dto.birth());
        donorCattle.setRegistrationNumber(dto.registrationNumber());

        return DonorCattleDto.toDonorCattleDto(donorCattleRepository.save(donorCattle));
    }
}
