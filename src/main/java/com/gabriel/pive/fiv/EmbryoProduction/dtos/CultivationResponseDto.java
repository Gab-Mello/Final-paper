package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

import java.util.List;

public record CultivationResponseDto(Long id, Long fivId,
                                     Integer totalEmbryos,
                                     Integer viableEmbryos,
                                     Integer embryosRegistered,
                                     List<EmbryoResponseDto> embryos) {

    public static CultivationResponseDto toCultivationResponseDto(EmbryoProduction embryoProduction){
        return new CultivationResponseDto(
                embryoProduction.getId(),
                embryoProduction.getFiv().getId(),
                embryoProduction.getTotalEmbryos(),
                embryoProduction.getViableEmbryos(),
                embryoProduction.getEmbryos().size(),
                EmbryoResponseDto.toEmbryoResponseDtoList(embryoProduction.getEmbryos())
                );
    }

    public static List<CultivationResponseDto> toCultivationDtoList(List<EmbryoProduction> list){
        List<CultivationResponseDto> listDto = list.stream().map(cultivation->
                CultivationResponseDto.toCultivationResponseDto(cultivation)).toList();
        return listDto;
    }

}
