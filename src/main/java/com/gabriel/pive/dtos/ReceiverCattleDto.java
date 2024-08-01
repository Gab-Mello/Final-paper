package com.gabriel.pive.dtos;

import com.gabriel.pive.entities.ReceiverCattle;

import java.time.LocalDate;
import java.util.List;


public record ReceiverCattleDto(Long id, String name, String breed, LocalDate birth) {

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
    public static List<ReceiverCattleDto> toReceiverCattleDtoList(List<ReceiverCattle> list){
        List<ReceiverCattleDto> listDto = list.stream().map(cattle->toReceiverCattleDto(cattle)).toList();
        return listDto;
    }
}
