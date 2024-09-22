package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.animals.services.DonorCattleService;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.ProductionRequestDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.ProductionResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.InvalidNumberOfEmbryosException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.OocyteCollectionAlreadyHasProduction;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.OocyteCollectionNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionService {

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private OocyteCollectionRepository oocyteCollectionRepository;

    @Autowired
    private FivService fivService;

    @Autowired
    private FivRepository fivRepository;

    @Autowired
    private DonorCattleService donorCattleService;

    public ProductionResponseDto newProduction(ProductionRequestDto dto){

        OocyteCollection oocyteCollection = oocyteCollectionRepository.findById(dto.oocyteCollectionId())
                .orElseThrow(OocyteCollectionNotFoundException::new);

        if(oocyteCollection.getEmbryoProduction() != null ){
            throw new OocyteCollectionAlreadyHasProduction();
        }

        if(dto.totalEmbryos() > oocyteCollection.getViableOocytes()){
            throw new InvalidNumberOfEmbryosException();
        }

        EmbryoProduction embryoProduction = dto.toProduction(oocyteCollection);

        Float EmbryosPercentage =
                ((float) embryoProduction.getTotalEmbryos() / embryoProduction.getOocyteCollection().getViableOocytes()) * 100;

        embryoProduction.setEmbryosPercentage(EmbryosPercentage);
        EmbryoProduction savedEmbryoProduction = productionRepository.save(embryoProduction);

        oocyteCollection.setEmbryoProduction(savedEmbryoProduction);
        oocyteCollectionRepository.save(oocyteCollection);

        fivService.updateTotalEmbryos(oocyteCollection.getFiv(), embryoProduction.getTotalEmbryos());

        donorCattleService.updateAverageViableEmbryos(embryoProduction.getOocyteCollection().getDonorCattle());

        return ProductionResponseDto.toProductionResponseDto(savedEmbryoProduction);

    }

    public List<ProductionResponseDto> getAllProductions(){
        return ProductionResponseDto.toCultivationDtoList(productionRepository.findAll());
    }

    public ProductionResponseDto getProductionById(Long id){
        EmbryoProduction embryoProduction = productionRepository.findById(id)
                .orElseThrow(ProductionNotFoundException::new);

        return ProductionResponseDto.toProductionResponseDto(embryoProduction);
    }



//    public CultivationResponseDto editCultivation(Long id, ProductionRequestDto dto){
//        EmbryoProduction embryoProduction = productionRepository.findById(id)
//                .orElseThrow(ProductionNotFoundException::new);
//
//        Fiv newFiv = fivRepository.findById(dto.fivId())
//                .orElseThrow(FivNotFoundException::new);
//
//        if (newFiv.getEmbryoProduction() != null){
//            throw  new FivAlreadyHasCultivation();
//        }
//
//        if (newFiv.getOocyteCollections().isEmpty()){
//            throw new FivDoesNotHaveOocyteCollectionException();
//        }
//
//        Fiv oldFiv = fivRepository.findByEmbryoProductionId(id);
//        oldFiv.setEmbryoProduction(null);
//        fivRepository.save(oldFiv);
//
//
//        embryoProduction.setFiv(newFiv);
//        embryoProduction.setTotalEmbryos(dto.totalEmbryos());
//        embryoProduction.setViableEmbryos(dto.viableEmbryos());
//
//        EmbryoProduction embryoProductionSaved = productionRepository.save(embryoProduction);
//
//        newFiv.setEmbryoProduction(embryoProductionSaved);
//        fivRepository.save(newFiv);
//
//        return CultivationResponseDto.toCultivationResponseDto(embryoProductionSaved);
//
//    }
}
