package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.fiv.cultivation.entities.Cultivation;

import java.util.List;

public record CultivationSummaryResponseDto(Long id,
                                            Integer totalEmbryos,
                                            Integer viableEmbryos
                                            ) {

    public static CultivationSummaryResponseDto toCultivationSummaryDto(Cultivation cultivation){
        if (cultivation == null){
            return null;
        }
        return new CultivationSummaryResponseDto(
                cultivation.getId(),
                cultivation.getTotalEmbryos(),
                cultivation.getViableEmbryos()
        );
    }


}
