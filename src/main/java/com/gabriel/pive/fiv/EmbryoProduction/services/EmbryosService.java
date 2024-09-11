package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.AllEmbryosAlreadyRegisteredException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.EmbryoNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
import com.gabriel.pive.fiv.pregnancy.repositories.PregnancyRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
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

    public EmbryoResponseDto saveEmbryo(EmbryoRequestDto dto){

        EmbryoProduction production = productionRepository.findById(dto.productionId())
                .orElseThrow(ProductionNotFoundException::new);

        if (production.getEmbryos().size() == production.getViableEmbryos()){
            throw new AllEmbryosAlreadyRegisteredException();
        }

        Embryo embryo = dto.toEmbryo(production);
        embryo.setEmbryoBull(production.getOocyteCollection().getBull());
        embryo.setEmbryoDonorCattle(production.getOocyteCollection().getDonorCattle());

        if (dto.destiny() == EmbryoDestiny.TRANSFERRED){
            ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                    .orElseThrow(ReceiverCattleNotFoundException::new);

            if (receiverCattle.getEmbryo() != null){
                throw new ReceiverCattleAlreadyHasEmbryoException();
            }

            embryo.setEmbryoReceiverCattle(receiverCattle);
            Pregnancy pregnancy = new Pregnancy(dto.date(), receiverCattle, PregnancyStatus.IN_PROGRESS);
            pregnancyRepository.save(pregnancy);
        }

        if (production.getEmbryos().size() == production.getViableEmbryos() - 1){
            Fiv fiv = production.getOocyteCollection().getFiv();
            fiv.setStatus(FivStatusEnum.COMPLETED);
            fivRepository.save(fiv);
        }

        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
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
