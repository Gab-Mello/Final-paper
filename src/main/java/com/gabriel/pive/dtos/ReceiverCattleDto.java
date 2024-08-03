package com.gabriel.pive.dtos;

import com.gabriel.pive.entities.ReceiverCattle;

import java.util.List;


public record ReceiverCattleDto(Long id, String name, String breed, Integer registrationNumber) {

    public ReceiverCattle toReceiverCattle(){
        return new ReceiverCattle(
                name,
                breed,
                registrationNumber);
    }

    public static ReceiverCattleDto toReceiverCattleDto(ReceiverCattle receiverCattle){
        return new ReceiverCattleDto(
                receiverCattle.getId(),
                receiverCattle.getName(),
                receiverCattle.getBreed(),
                receiverCattle.getRegistrationNumber()
        );
    }
    public static List<ReceiverCattleDto> toReceiverCattleDtoList(List<ReceiverCattle> list){
        List<ReceiverCattleDto> listDto = list.stream().map(cattle->toReceiverCattleDto(cattle)).toList();
        return listDto;
    }
}
