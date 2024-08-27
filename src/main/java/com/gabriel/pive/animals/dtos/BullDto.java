package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.Bull;

import java.util.List;

public record BullDto(Long id, String name, String registrationNumber) {

    public Bull toBull(){
        return new Bull(
                name,
                registrationNumber);
    }

    public static BullDto toBullDto(Bull bull){

        if (bull == null){
            return null;
        }

        return new BullDto(
                bull.getId(),
                bull.getName(),
                bull.getRegistrationNumber()
        );
    }
    public static List<BullDto> toBullDtoList(List<Bull> list){
        List<BullDto> listDto = list.stream().map(bull->toBullDto(bull)).toList();
        return listDto;
    }
}
