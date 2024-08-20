package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;

import java.util.List;

public record CultivationResponsePostDto(Long id, Long fivId,
                                         List<Embryo> embryos, Integer totalEmbryos,
                                         Integer viableEmbryos) {

    public static CultivationResponseDto toCultivationResponsePostDto(Cultivation cultivation, Long fivId){
        return new CultivationResponseDto(
                cultivation.getId(),
                cultivation.getEmbryos(),
                cultivation.getTotalEmbryos(),
                cultivation.getViableEmbryos());
    }


}
