package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.BullNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.fiv.embryoproduction.entities.Embryo;
import com.gabriel.pive.fiv.embryoproduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocytecollection.repositories.OocyteCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BullService {

    private final BullRepository bullRepository;
    private final EmbryoRepository embryoRepository;
    private final OocyteCollectionRepository oocyteCollectionRepository;

    @Transactional
    public BullDto create(BullDto dto){

        if (bullRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Um touro com este número de registro já existe.");
        }
        Bull bull = bullRepository.save(dto.toBull());
        return BullDto.toBullDto(bull);
    }

    public List<BullDto> findAll(){
        List<Bull> list = bullRepository.findAll();
        return BullDto.toBullDtoList(list);
    }

    public List<BullDto> getBullsWithHighestEmbryoPercentage() {
        List<Bull> bulls = bullRepository.findBullsWithHighestEmbryoPercentage();
        return BullDto.toBullDtoList(bulls);
    }

    @Transactional
    public void updateAverageViableEmbryos(Bull bull) {
        List<OocyteCollection> oocyteCollections = bull.getOocyteCollections();

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
            bull.setAverageEmbryoPercentage(averageEmbryosPercentage);
        } else {
            bull.setAverageEmbryoPercentage(0.0);
        }
        bullRepository.save(bull);
    }


    public BullDto findById(Long id){
        Bull bull = bullRepository.findById(id)
                .orElseThrow(BullNotFoundException::new);
        return BullDto.toBullDto(bull);
    }

    public List<BullDto> findByRegistrationNumber(String registrationNumber){
        return BullDto.toBullDtoList(bullRepository.
                findByRegistrationNumberStartingWith(registrationNumber.toUpperCase()));
    }

    @Transactional
    public void delete(Long id){
        Bull bull = bullRepository.findById(id)
                .orElseThrow(BullNotFoundException::new);

        List<Embryo> embryos = embryoRepository.findAllByEmbryoBull(bull);
        for (Embryo embryo : embryos) {
            embryo.setEmbryoBull(null);
            embryoRepository.save(embryo);
        }

        List<OocyteCollection> oocyteCollections = oocyteCollectionRepository.findAllByBull(bull);
        for (OocyteCollection collection : oocyteCollections) {
            collection.setBull(null);
            oocyteCollectionRepository.save(collection);
        }

        bullRepository.deleteById(id);
    }

    @Transactional
    public BullDto edit(Long id, BullDto dto){
        Bull bull = bullRepository.findById(id)
                .orElseThrow(BullNotFoundException::new);

        if (bullRepository.findByRegistrationNumber(dto.registrationNumber()) != bull
                && bullRepository.findByRegistrationNumber(dto.registrationNumber()) != null  ){
            throw new RegistrationNumberAlreadyExistsException("Um touro com este número de registro já existe.");
        }

        bull.setName(dto.name());
        bull.setRegistrationNumber(dto.registrationNumber());

        return BullDto.toBullDto(bullRepository.save(bull));
    }
}
