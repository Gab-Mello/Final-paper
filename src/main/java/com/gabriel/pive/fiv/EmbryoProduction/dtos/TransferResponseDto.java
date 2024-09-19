package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;

import java.time.LocalDate;
import java.util.List;

public record TransferResponseDto(Long id,
                                  Long fivId,
                                  LocalDate date,
                                  String responsible,
                                  String farm,
                                  List<EmbryoResponseDto> embryos) {

    public static TransferResponseDto toTransferResponseDto(EmbryoTransfer transfer){
        return new TransferResponseDto(
                transfer.getId(),
                transfer.getFivTransfer().getId(),
                transfer.getDate(),
                transfer.getResponsible(),
                transfer.getFarm(),
                EmbryoResponseDto.toEmbryoResponseDtoList(transfer.getEmbryos())
        );
    }

    public static List<TransferResponseDto> toTrasnferResponseDtoList(List<EmbryoTransfer> list){
        List<TransferResponseDto> listDto = list.stream().map(transfer->
                TransferResponseDto.toTransferResponseDto(transfer)).toList();
        return listDto;
    }
}
