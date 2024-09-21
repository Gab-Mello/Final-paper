package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;

import java.text.DecimalFormat;

public record BullAverageEmbryoDto(BullDto bull, String averageEmbryoPercentage) {

    public static BullAverageEmbryoDto toBullAverageEmbryoDto(Bull bull, Double averageEmbryoPercentage){

        Double percentage = averageEmbryoPercentage;
        String formattedPercentage;

        if (percentage == null || percentage == 0){
            formattedPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedPercentage = df.format(percentage) + "%";
        }
        return new BullAverageEmbryoDto(BullDto.toBullDto(bull),
                                        formattedPercentage);
    }

}
