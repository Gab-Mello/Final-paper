package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.Bull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.text.DecimalFormat;
import java.util.List;

public record BullDto(Long id,
                      String name,
                      @NotBlank(message = "Número de identificação em branco.") String registrationNumber,
                      String averageEmbryoPercentage) {

    public Bull toBull(){
        return new Bull(
                name,
                registrationNumber);
    }

    public static BullDto toBullDto(Bull bull){

        if (bull == null){
            return null;
        }

        Double percentage = bull.getAverageEmbryoPercentage();
        String formattedPercentage;

        if (percentage == null || percentage == 0){
            formattedPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedPercentage = df.format(percentage) + "%";
        }

        return new BullDto(
                bull.getId(),
                bull.getName(),
                bull.getRegistrationNumber(),
                formattedPercentage
        );
    }
    public static List<BullDto> toBullDtoList(List<Bull> list){
        List<BullDto> listDto = list.stream().map(bull->toBullDto(bull)).toList();
        return listDto;
    }
}
