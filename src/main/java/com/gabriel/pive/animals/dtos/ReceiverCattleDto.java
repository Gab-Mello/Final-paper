package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record ReceiverCattleDto(Long id,
                                @NotBlank(message = "Nome da receptora em branco.") String name,
                                @NotBlank(message = "Raça em branco.") String breed,
                                @NotBlank(message = "Número de identificação em branco.") String registrationNumber,
                                Pregnancy pregnancy) {

    public ReceiverCattle toReceiverCattle(){
        return new ReceiverCattle(
                name,
                breed,
                registrationNumber);
    }

    public static ReceiverCattleDto toReceiverCattleDto(ReceiverCattle receiverCattle){
        if (receiverCattle == null){
            return null;
        }
        return new ReceiverCattleDto(
                receiverCattle.getId(),
                receiverCattle.getName(),
                receiverCattle.getBreed(),
                receiverCattle.getRegistrationNumber(),
                receiverCattle.getPregnancy()
        );
    }
    public static List<ReceiverCattleDto> toReceiverCattleDtoList(List<ReceiverCattle> list){
        List<ReceiverCattleDto> listDto = list.stream().
                map(cattle->toReceiverCattleDto(cattle)).
                toList();

        return listDto;
    }
}
