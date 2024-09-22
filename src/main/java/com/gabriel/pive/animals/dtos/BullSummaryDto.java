package com.gabriel.pive.animals.dtos;

import com.gabriel.pive.animals.entities.Bull;

import java.text.DecimalFormat;

public record BullSummaryDto(Long id, String name, String registrationNumber) {

    public static BullDto toBullSummaryDto(Bull bull){
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
}
