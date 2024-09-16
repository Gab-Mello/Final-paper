package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.*;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.EmbryoNotFoundException;

import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.pregnancy.repositories.PregnancyRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbryosService {

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private FivRepository fivRepository;

    @Autowired
    private PregnancyRepository pregnancyRepository;

    @Autowired
    private ProductionService productionService;

    @Autowired
    private FivService fivService;

    public ProductionResponseDto frozenEmbryos(FrozenEmbryosDto dto){

        EmbryoProduction production = productionRepository.findById(dto.productionId())
                .orElseThrow(ProductionNotFoundException::new);

        Integer embryosQuantity = dto.embryosQuantity();

        fivService.updateFivWithFrozenOrDiscardedEmbryos(production,embryosQuantity);

        DonorCattle donorCattle = production.getOocyteCollection().getDonorCattle();
        Bull bull = production.getOocyteCollection().getBull();

        for (int i = 0 ; i <embryosQuantity; i++){
            Embryo embryo = new Embryo(production, donorCattle, bull, EmbryoDestiny.FROZEN);
            embryoRepository.save(embryo);
            production.getEmbryos().add(embryo);
        }

        return ProductionResponseDto.toProductionResponseDto(production);
    }

    public void discardedEmbryos(DiscardedEmbryosDto dto){

    }

//    public EmbryoResponseDto editEmbryo(Long id, EmbryoRequestDto dto){
//        Embryo embryo = embryoRepository.findById(id).
//                orElseThrow(EmbryoNotFoundException::new);
//
//        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
//                .orElseThrow(ReceiverCattleNotFoundException::new);
//
//        embryo.setEmbryoReceiverCattle(receiverCattle);
//        embryo.setFrozen(dto.frozen());
//
//        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
//    }

    public List<EmbryoResponseDto> getAllEmbryos(){
        return EmbryoResponseDto.toEmbryoResponseDtoList(embryoRepository.findAll());
    }

    public EmbryoResponseDto getEmbryoById(Long id){
        Embryo embryo = embryoRepository.findById(id)
                .orElseThrow(EmbryoNotFoundException::new);

        return EmbryoResponseDto.toEmbryoResponseDto(embryo);
    }
}
