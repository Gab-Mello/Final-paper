package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.FivAlreadyHasCultivation;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.FivDoesNotHaveOocyteCollectionException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.MoreViableThanTotalEmbryosException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private FivRepository fivRepository;

    public CultivationResponseDto newProduction(CultivationRequestDto dto){

        Fiv fiv = fivRepository.findById(dto.fivId())
                .orElseThrow(FivNotFoundException::new);

        if (fiv.getOocyteCollections().isEmpty()){
            throw new FivDoesNotHaveOocyteCollectionException();
        }

        if (fiv.getEmbryoProduction() != null){
            throw  new FivAlreadyHasCultivation();
        }

        if (dto.viableEmbryos() > dto.totalEmbryos()){
            throw new MoreViableThanTotalEmbryosException();
        }

        EmbryoProduction embryoProduction = dto.toCultivation(fiv);

        EmbryoProduction savedEmbryoProduction = productionRepository.save(embryoProduction);

        fiv.setEmbryoProduction(savedEmbryoProduction);
        fivRepository.save(fiv);

        return CultivationResponseDto.toCultivationResponseDto(savedEmbryoProduction);

    }

    public List<CultivationResponseDto> getAllCultivations(){
        return CultivationResponseDto.toCultivationDtoList(productionRepository.findAll());
    }

    public CultivationResponseDto getCultivationById(Long id){
        EmbryoProduction embryoProduction = productionRepository.findById(id)
                .orElseThrow(ProductionNotFoundException::new);

        return CultivationResponseDto.toCultivationResponseDto(embryoProduction);
    }

    public CultivationResponseDto editCultivation(Long id, CultivationRequestDto dto){
        EmbryoProduction embryoProduction = productionRepository.findById(id)
                .orElseThrow(ProductionNotFoundException::new);

        Fiv newFiv = fivRepository.findById(dto.fivId())
                .orElseThrow(FivNotFoundException::new);

        if (newFiv.getEmbryoProduction() != null){
            throw  new FivAlreadyHasCultivation();
        }

        if (newFiv.getOocyteCollections().isEmpty()){
            throw new FivDoesNotHaveOocyteCollectionException();
        }

        Fiv oldFiv = fivRepository.findByEmbryoProductionId(id);
        oldFiv.setEmbryoProduction(null);
        fivRepository.save(oldFiv);


        embryoProduction.setFiv(newFiv);
        embryoProduction.setTotalEmbryos(dto.totalEmbryos());
        embryoProduction.setViableEmbryos(dto.viableEmbryos());

        EmbryoProduction embryoProductionSaved = productionRepository.save(embryoProduction);

        newFiv.setEmbryoProduction(embryoProductionSaved);
        fivRepository.save(newFiv);

        return CultivationResponseDto.toCultivationResponseDto(embryoProductionSaved);

    }
}
