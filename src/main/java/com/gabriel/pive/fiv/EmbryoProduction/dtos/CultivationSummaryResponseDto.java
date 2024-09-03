package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

public record CultivationSummaryResponseDto(Long id,
                                            Integer totalEmbryos,
                                            Integer viableEmbryos
                                            ) {

    public static CultivationSummaryResponseDto toCultivationSummaryDto(EmbryoProduction embryoProduction){
        if (embryoProduction == null){
            return null;
        }
        return new CultivationSummaryResponseDto(
                embryoProduction.getId(),
                embryoProduction.getTotalEmbryos(),
                embryoProduction.getViableEmbryos()
        );
    }


}
