package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverCattleService {

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    public ReceiverCattleDto create(ReceiverCattleDto dto){
        if (receiverCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma receptora com este número de registro já existe.");
        }
        ReceiverCattle receiverCattle = receiverCattleRepository.save(dto.toReceiverCattle());
        return ReceiverCattleDto.toReceiverCattleDto(receiverCattle);
    }

    public List<ReceiverCattleDto> findAll(){
        List<ReceiverCattle> list = receiverCattleRepository.findAll();
        return ReceiverCattleDto.toReceiverCattleDtoList(list);
    }

    public ReceiverCattleDto findByRegistrationNumber(String registrationNumber){
        return ReceiverCattleDto.toReceiverCattleDto(receiverCattleRepository.
                findByRegistrationNumber(registrationNumber));
    }

    public ReceiverCattleDto findById(Long id){
        ReceiverCattle receiverCattle = receiverCattleRepository.findById(id)
                .orElseThrow(() -> new ReceiverCattleNotFoundException());
        return ReceiverCattleDto.toReceiverCattleDto(receiverCattle);
    }

    public void delete(Long id){
        receiverCattleRepository.deleteById(id);
    }

    public ReceiverCattleDto edit(Long id, ReceiverCattleDto dto){
        ReceiverCattle receiverCattle = receiverCattleRepository.findById(id)
                .orElseThrow(() -> new ReceiverCattleNotFoundException());

        if (receiverCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma receptora com este número de registro já existe.");
        }

        receiverCattle.setName(dto.name());
        receiverCattle.setBreed(dto.breed());
        receiverCattle.setRegistrationNumber(dto.registrationNumber());

        return ReceiverCattleDto.toReceiverCattleDto(receiverCattleRepository.save(receiverCattle));
    }

    public List<ReceiverCattleDto> getAvailableReceivers(){
        return ReceiverCattleDto.toReceiverCattleDtoList(receiverCattleRepository.findByEmbryoIsNull());
    }
}
