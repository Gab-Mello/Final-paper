package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

public record ProductionSummaryDto(Integer totalEmbryos, Float embryosPercentage) {

    public static ProductionSummaryDto toProductionSummaryDto(EmbryoProduction production){
        if (production == null){
            return null;
        }
        return new ProductionSummaryDto(production.getTotalEmbryos(),
                                        production.getEmbryosPercentage());
    }
}
