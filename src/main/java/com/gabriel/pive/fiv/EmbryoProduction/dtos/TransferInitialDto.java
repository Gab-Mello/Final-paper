package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.entities.Fiv;

import java.time.LocalDate;
import java.util.List;

public record TransferInitialDto(Long fivId,
                                 LocalDate date,
                                 String responsible,
                                 String farm) {

    public EmbryoTransfer toTransfer(Fiv fiv){
        if (fiv == null){
            return null;
        }
        return new EmbryoTransfer(
                fiv,
                date,
                responsible,
                farm
        );
    }

    public static TransferInitialDto toTransferDto(EmbryoTransfer embryoTransfer){
        if(embryoTransfer==null){
            return null;
        }
        return new TransferInitialDto(
                embryoTransfer.getFivTransfer().getId(),
                embryoTransfer.getDate(),
                embryoTransfer.getResponsible(),
                embryoTransfer.getFarm()
        );
    }

    public static List<TransferInitialDto> toTrasnferDtoList(List<EmbryoTransfer> list){
        List<TransferInitialDto> listDto = list.stream().map(transfer->
                TransferInitialDto.toTransferDto(transfer)).toList();
        return listDto;
    }
}
