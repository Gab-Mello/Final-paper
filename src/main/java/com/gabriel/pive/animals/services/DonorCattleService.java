package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.*;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.InvalidDateException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorCattleService {

    @Autowired
    private DonorCattleRepository donorCattleRepository;

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private OocyteCollectionRepository oocyteCollectionRepository;

    @Autowired
    private FivRepository fivRepository;

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

    public List<DonorCattleAverageOocytesDto> getDonorsWithHighestOocytesCollected() {
        List<Object[]> results = donorCattleRepository.findDonorsWithHighestOocytesCollected();

        return results.stream()
                .map(result -> new DonorCattleAverageOocytesDto(
                        DonorCattleSummaryDto.toDonorCattleSummaryDto((DonorCattle) result[0]), // DonorCattle entity
                        formatDoubleToTwoDecimals((Double) result[1]) // Average of viable oocytes
                ))
                .collect(Collectors.toList());
    }

    public List<DonorCattleAverageEmbryoDto> getDonorsWithHighestEmbryoPercentage() {
        List<Object[]> results = donorCattleRepository.findDonorsWithHighestEmbryoPercentage();

        return results.stream()
                .map(result -> DonorCattleAverageEmbryoDto.toDonorCattleAverageEmbryoDto(
                        (DonorCattle) result[0], // DonorCattle entity
                        (Double) result[1]) // Average percentage
                )
                .collect(Collectors.toList());
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
                findByRegistrationNumberStartingWith(registrationNumber));
    }

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
