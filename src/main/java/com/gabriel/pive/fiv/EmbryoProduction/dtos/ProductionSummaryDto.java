package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

import java.text.DecimalFormat;

public record ProductionSummaryDto(Integer totalEmbryos, String formattedPercentage) {

    public static ProductionSummaryDto toProductionSummaryDto(EmbryoProduction production){
        if (production == null){
            return null;
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedPercentage = df.format(production.getEmbryosPercentage()) + "%";
        return new ProductionSummaryDto(production.getTotalEmbryos(),
                                        formattedPercentage);
    }
}
