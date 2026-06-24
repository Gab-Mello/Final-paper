package com.gabriel.pive.fiv.embryoproduction.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.services.BullService;
import com.gabriel.pive.animals.services.DonorCattleService;
import com.gabriel.pive.fiv.embryoproduction.dtos.ProductionRequestDto;
import com.gabriel.pive.fiv.embryoproduction.dtos.ProductionResponseDto;
import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.embryoproduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.embryoproduction.exceptions.InvalidNumberOfEmbryosException;
import com.gabriel.pive.fiv.embryoproduction.exceptions.OocyteCollectionAlreadyHasProduction;
import com.gabriel.pive.fiv.embryoproduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.embryoproduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.embryoproduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocytecollection.exceptions.OocyteCollectionNotFoundException;
import com.gabriel.pive.fiv.oocytecollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductionService {

    private final ProductionRepository productionRepository;
    private final OocyteCollectionRepository oocyteCollectionRepository;
    private final FivService fivService;
    private final FivRepository fivRepository;
    private final DonorCattleService donorCattleService;
    private final BullService bullService;
    private final EmbryoRepository embryoRepository;

    @Transactional
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
        bullService.updateAverageViableEmbryos(embryoProduction.getOocyteCollection().getBull());

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

    @Transactional
    public void updateTransferredEmbryosNumber(EmbryoProduction production){
        production.setTransferredEmbryosNumber(production.getTransferredEmbryosNumber() + 1);

        Fiv fiv = production.getOocyteCollection().getFiv();
        fiv.setFivTransferredEmbryosNumber(fiv.getFivTransferredEmbryosNumber() + 1);

        fivRepository.save(fiv);
        productionRepository.save(production);
    }

    @Transactional
    public void updateFrozenOrDiscardedEmbryosNumber(EmbryoProduction production, Integer number, Boolean is_frozen){
        Fiv fiv = production.getOocyteCollection().getFiv();

        if (is_frozen){
            production.setFrozenEmbryosNumber(production.getFrozenEmbryosNumber() + number);
            fiv.setFivFrozenEmbryosNumber(fiv.getFivFrozenEmbryosNumber() + number);
        }
        else {
            production.setDiscardedEmbryosNumber(production.getDiscardedEmbryosNumber() + number);
            fiv.setFivDiscardedEmbryosNumber(fiv.getFivDiscardedEmbryosNumber() + number);
        }

        productionRepository.save(production);
        fivRepository.save(fiv);
    }


}
