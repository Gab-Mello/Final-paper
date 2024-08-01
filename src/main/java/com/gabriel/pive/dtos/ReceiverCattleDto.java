package com.gabriel.pive.dtos;

import com.gabriel.pive.entities.ReceiverCattle;

import java.time.LocalDateTime;

public record ReceiverCattleDto(Long id, String name, String breed, LocalDateTime birth) {

    public ReceiverCattle toReceiverCattle(){
        return new ReceiverCattle(
                name,
                breed,
                birth);
    }

    public static ReceiverCattleDto toReceiverCattleDto(ReceiverCattle receiverCattle){
        return new ReceiverCattleDto(
                receiverCattle.getId(),
                receiverCattle.getName(),
                receiverCattle.getBreed(),
                receiverCattle.getBirth()
        );
    }
}
