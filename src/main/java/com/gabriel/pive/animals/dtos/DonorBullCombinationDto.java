package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;

import java.text.DecimalFormat;

public record DonorBullCombinationDto(DonorCattleDto donor, BullDto bull, String averageCombinationEmbryosPercentage) {

    public static DonorBullCombinationDto toDonorBullCombinationDto(DonorCattleDto donor, BullDto bull, Double averageCombinationEmbryosPercentage){
        Double percentage = averageCombinationEmbryosPercentage;
        String formattedPercentage;

        if (percentage == null || percentage == 0){
            formattedPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedPercentage = df.format(percentage) + "%";
        }
        return new DonorBullCombinationDto(donor, bull, formattedPercentage);
    }

}
