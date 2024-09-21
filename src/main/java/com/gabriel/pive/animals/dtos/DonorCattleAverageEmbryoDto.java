package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.DonorCattle;

import java.text.DecimalFormat;

public record DonorCattleAverageEmbryoDto(DonorCattleDto donor, String averageEmbryoPercentage) {

    public static DonorCattleAverageEmbryoDto toDonorCattleAverageEmbryoDto(DonorCattle donor, Double averageEmbryoPercentage){

        Double percentage = averageEmbryoPercentage;
        String formattedPercentage;

        if (percentage == null || percentage == 0){
            formattedPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedPercentage = df.format(percentage) + "%";
        }
        return new DonorCattleAverageEmbryoDto(DonorCattleSummaryDto.toDonorCattleSummaryDto(donor),
                                                formattedPercentage);
    }
}
