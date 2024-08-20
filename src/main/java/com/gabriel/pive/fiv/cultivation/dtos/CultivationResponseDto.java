package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;

import java.util.List;

public record CultivationResponseDto(Long id,
                                     List<Embryo> embryos, Integer totalEmbryos,
                                     Integer viableEmbryos) {

    public static CultivationResponseDto toCultivationResponseDto(Cultivation cultivation){
        return new CultivationResponseDto(
                cultivation.getId(),
                cultivation.getEmbryos(),
                cultivation.getTotalEmbryos(),
                cultivation.getViableEmbryos());
    }

}
