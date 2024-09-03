package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.CultivationSummaryResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;

import java.time.LocalDate;
import java.util.List;

public record FivResponseDto(Long id, FivStatusEnum status,
                             LocalDate date, String farm,
                             String laboratory, String client,
                             String veterinarian, String technical,
                             String TE, List<OocyteCollectionResponseDto> oocyteCollections,
                             Integer totalOocytesCollected, Integer totalViableOocytesCollected,
                             CultivationSummaryResponseDto cultivation) {


    public static FivResponseDto toFivResponseDto(Fiv fiv){
        return new FivResponseDto(
                fiv.getId(),
                fiv.getStatus(),
                fiv.getDate(),
                fiv.getFarm(),
                fiv.getLaboratory(),
                fiv.getClient(),
                fiv.getVeterinarian(),
                fiv.getTechnical(),
                fiv.getTE(),
                OocyteCollectionResponseDto.toOocyteCollectionDtoList(fiv.getOocyteCollections()),
                fiv.getTotalOocytesCollected(),
                fiv.getTotalViableOocytesCollected(),
                CultivationSummaryResponseDto.toCultivationSummaryDto(fiv.getEmbryoProduction()));
    }

    public static List<FivResponseDto> toFivResponseDtoList(List<Fiv> list){
        List<FivResponseDto> listDto = list.stream().map(fiv -> toFivResponseDto(fiv)).toList();
        return listDto;
    }
}
