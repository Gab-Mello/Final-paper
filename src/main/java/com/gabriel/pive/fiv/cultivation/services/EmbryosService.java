package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.EmbryoResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.cultivation.exceptions.AllEmbryosAlreadyRegisteredException;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.EmbryoNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.cultivation.repositories.CultivationRepository;
import com.gabriel.pive.fiv.cultivation.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.repositories.FivRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private CultivationRepository cultivationRepository;

    @Autowired
    private FivRepository fivRepository;

    public EmbryoResponseDto saveEmbryo(EmbryoRequestDto dto){

        Cultivation cultivation = cultivationRepository.findById(dto.cultivationId())
                .orElseThrow(CultivationNotFoundException::new);

        if (dto.frozen()){
            Embryo embryo = dto.toEmbryo(null, cultivation);
            embryo.setEmbryoBull(cultivation.getFiv().getOocyteCollection().getBull());
            embryo.setEmbryoDonorCattle(cultivation.getFiv().getOocyteCollection().getDonorCattle());
            return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
        }

        if (cultivation.getEmbryos().size() == cultivation.getViableEmbryos()){
            throw new AllEmbryosAlreadyRegisteredException();
        }

        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(ReceiverCattleNotFoundException::new);

        if (receiverCattle.getEmbryo() != null){
            throw new ReceiverCattleAlreadyHasEmbryoException();
        }

        if (cultivation.getEmbryos().size() == cultivation.getViableEmbryos() - 1){
            Fiv fiv = cultivation.getFiv();
            fiv.setStatus(FivStatusEnum.COMPLETED);
            fivRepository.save(fiv);
        }
        Embryo embryo = dto.toEmbryo(receiverCattle, cultivation);

        embryo.setEmbryoBull(cultivation.getFiv().getOocyteCollection().getBull());
        embryo.setEmbryoDonorCattle(cultivation.getFiv().getOocyteCollection().getDonorCattle());


        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
    }

    public EmbryoResponseDto editEmbryo(Long id, EmbryoRequestDto dto){
        Embryo embryo = embryoRepository.findById(id).
                orElseThrow(EmbryoNotFoundException::new);

        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverCattleId())
                .orElseThrow(ReceiverCattleNotFoundException::new);

        embryo.setEmbryoReceiverCattle(receiverCattle);
        embryo.setFrozen(dto.frozen());

        return EmbryoResponseDto.toEmbryoResponseDto(embryoRepository.save(embryo));
    }

    public List<EmbryoResponseDto> getAllEmbryos(){
        return EmbryoResponseDto.toEmbryoResponseDtoList(embryoRepository.findAll());
    }

    public EmbryoResponseDto getEmbryoById(Long id){
        Embryo embryo = embryoRepository.findById(id)
                .orElseThrow(EmbryoNotFoundException::new);

        return EmbryoResponseDto.toEmbryoResponseDto(embryo);
    }
}
