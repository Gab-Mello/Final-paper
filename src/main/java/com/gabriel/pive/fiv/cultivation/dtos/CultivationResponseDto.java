package com.gabriel.pive.fiv.cultivation.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;

import java.util.List;

public record CultivationResponseDto(Long id, Long fivId,
                                     Integer totalEmbryos,
                                     Integer viableEmbryos,
                                     Integer embryosRegistered,
                                     List<EmbryoResponseDto> embryos) {

    public static CultivationResponseDto toCultivationResponseDto(Cultivation cultivation){
        return new CultivationResponseDto(
                cultivation.getId(),
                cultivation.getFiv().getId(),
                cultivation.getTotalEmbryos(),
                cultivation.getViableEmbryos(),
                cultivation.getEmbryos().size(),
                EmbryoResponseDto.toEmbryoResponseDtoList(cultivation.getEmbryos())
                );
    }

    public static List<CultivationResponseDto> toCultivationDtoList(List<Cultivation> list){
        List<CultivationResponseDto> listDto = list.stream().map(cultivation->
                CultivationResponseDto.toCultivationResponseDto(cultivation)).toList();
        return listDto;
    }

}
