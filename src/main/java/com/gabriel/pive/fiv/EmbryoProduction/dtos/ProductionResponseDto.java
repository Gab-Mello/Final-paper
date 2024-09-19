package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;

import java.util.List;

public record ProductionResponseDto(Long id, Long oocyteCollectionId,
                                    Integer totalEmbryos,
                                    Integer embryosRegistered,
                                    List<EmbryoResponseDto> embryos) {

    public static ProductionResponseDto toProductionResponseDto(EmbryoProduction embryoProduction){
        return new ProductionResponseDto(
                embryoProduction.getId(),
                embryoProduction.getOocyteCollection().getId(),
                embryoProduction.getTotalEmbryos(),
                embryoProduction.getEmbryosRegistered(),
                EmbryoResponseDto.toEmbryoResponseDtoList(embryoProduction.getEmbryos())
                );
    }

    public static List<ProductionResponseDto> toCultivationDtoList(List<EmbryoProduction> list){
        List<ProductionResponseDto> listDto = list.stream().map(cultivation->
                ProductionResponseDto.toProductionResponseDto(cultivation)).toList();
        return listDto;
    }

}
