package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.cultivation.repositories.CultivationRepository;
import com.gabriel.pive.fiv.cultivation.repositories.EmbryoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbryosService {

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    @Autowired
    private CultivationRepository cultivationRepository;

    public EmbryoResponseDto saveEmbryo(EmbryoRequestDto dto){

        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(()-> new ReceiverCattleNotFoundException("Receiver Cattle not found"));

        if (receiverCattle.getEmbryo() != null){
            throw new ReceiverCattleAlreadyHasEmbryoException("This Receiver cattle already has a embryo");
        }

        Cultivation cultivation = cultivationRepository.findById(dto.cultivationId())
                .orElseThrow(()-> new CultivationNotFoundException("Cultivation not found"));


        Embryo embryo = dto.toEmbryo(receiverCattle, cultivation);

        embryo.setEmbryoBull(cultivation.getFiv().getOocyteCollection().getBull());
        embryo.setEmbryoDonorCattle(cultivation.getFiv().getOocyteCollection().getDonorCattle());


        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
    }

    public EmbryoResponseDto editEmbryo(Long id, EmbryoRequestDto dto){
        Embryo embryo = embryoRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Embryo not found"));

        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(()-> new EntityNotFoundException("Receiver Cattle not found"));

        embryo.setEmbryoReceiverCattle(receiverCattle);
        embryo.setFrozen(dto.frozen());

        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
    }
}