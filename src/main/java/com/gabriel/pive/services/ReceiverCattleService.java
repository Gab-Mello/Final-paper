package com.gabriel.pive.services;

import com.gabriel.pive.dtos.ReceiverCattleDto;
import com.gabriel.pive.entities.ReceiverCattle;
import com.gabriel.pive.repositories.ReceiverCattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverCattleService {

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    public ReceiverCattleDto create(ReceiverCattleDto dto){
        ReceiverCattle receiverCattle = receiverCattleRepository.save(dto.toReceiverCattle());
        return ReceiverCattleDto.toReceiverCattleDto(receiverCattle);
    }

    public List<ReceiverCattleDto> findAll(){
        List<ReceiverCattle> list = receiverCattleRepository.findAll();
        return ReceiverCattleDto.toReceiverCattleDtoList(list);
    }

    public ReceiverCattleDto findById(Long id){
        Optional<ReceiverCattle> receiverCattle = receiverCattleRepository.findById(id);
        if (receiverCattle.isEmpty()){
            return null;
        }
        return ReceiverCattleDto.toReceiverCattleDto(receiverCattle.get());
    }

    public void delete(Long id){
        receiverCattleRepository.deleteById(id);
    }

    public ReceiverCattleDto edit(Long id, ReceiverCattleDto dto){
        Optional<ReceiverCattle> optionalReceiverCattle= receiverCattleRepository.findById(id);
        ReceiverCattle receiverCattle = optionalReceiverCattle.get();

        receiverCattle.setName(dto.name());
        receiverCattle.setBirth(dto.birth());
        receiverCattle.setBreed(dto.breed());

        return ReceiverCattleDto.toReceiverCattleDto(receiverCattleRepository.save(receiverCattle));
    }
}
