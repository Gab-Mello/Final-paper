package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import java.text.DecimalFormat;

public record ProductionSummaryDto(Long id,
                                   Integer totalEmbryos,
                                   String embryosPercentage,
                                   Integer embryosRegistered,
                                   Integer numberTransferredEmbryos,
                                   Integer numberFrozenEmbryos,
                                   Integer numberDiscardedEmbryos,
                                   Integer numberPregnancies,
                                   String pregnancyPercentage
                                    ) {

    public static ProductionSummaryDto toProductionSummaryDto(EmbryoProduction production){

        if (production == null){
            return null;
        }

        String formattedEmbryosPercentage = formatPercentage(production.getEmbryosPercentage());
        String formattedPregnancyPercentage =  formatPercentage(production.getPregnancyPercentage());

        return new ProductionSummaryDto(production.getId(),
                                        production.getTotalEmbryos(),
                                        formattedEmbryosPercentage,
                                        production.getEmbryosRegistered(),
                                        production.getTransferredEmbryosNumber(),
                                        production.getFrozenEmbryosNumber(),
                                        production.getDiscardedEmbryosNumber(),
                                        production.getTotalPregnancy(),
                                        formattedPregnancyPercentage
                                        );
    }

    public static String formatPercentage(Float percentage){
        if (percentage == null || percentage == 0){
            return "0.00%";
        }
        else{
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(percentage) + "%";
        }
    }
}
