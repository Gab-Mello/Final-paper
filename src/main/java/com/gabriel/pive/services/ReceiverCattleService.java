package com.gabriel.pive.services;

import com.gabriel.pive.dtos.ReceiverCattleDto;
import com.gabriel.pive.repositories.ReceiverCattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiverCattleService {

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    public ReceiverCattleDto create(ReceiverCattleDto dto){
        receiverCattleRepository.save(dto.toReceiverCattle());

        return dto;
    }
}
