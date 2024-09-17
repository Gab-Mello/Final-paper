package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

import java.text.DecimalFormat;

public record ProductionSummaryDto(Integer totalEmbryos, String embryosPercentage) {

    public static ProductionSummaryDto toProductionSummaryDto(EmbryoProduction production){
        if (production == null){
            return null;
        }

        Float percentage = production.getEmbryosPercentage();
        String formattedPercentage;

        if (percentage == null || percentage == 0){
            formattedPercentage = "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            formattedPercentage = df.format(percentage) + "%";
        }

        return new ProductionSummaryDto(production.getTotalEmbryos(),
                                        formattedPercentage);
    }
}
