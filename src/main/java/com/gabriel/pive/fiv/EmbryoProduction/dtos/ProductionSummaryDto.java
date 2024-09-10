package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

public record ProductionSummaryDto(Integer totalEmbryos,
                                   Integer viableEmbryos) {

    public static ProductionSummaryDto toProductionSummaryDto(EmbryoProduction production){
        return new ProductionSummaryDto(production.getTotalEmbryos(), production.getViableEmbryos());
    }
}
