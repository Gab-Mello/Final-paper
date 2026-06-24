package com.gabriel.pive.fiv.embryoproduction.dtos;

import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;

public record CultivationSummaryResponseDto(Long id,
                                            Integer totalEmbryos
                                            ) {

    public static CultivationSummaryResponseDto toCultivationSummaryDto(EmbryoProduction embryoProduction){
        if (embryoProduction == null){
            return null;
        }
        return new CultivationSummaryResponseDto(
                embryoProduction.getId(),
                embryoProduction.getTotalEmbryos()
        );
    }


}
