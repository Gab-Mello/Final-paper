package com.gabriel.pive.fiv.EmbryoProduction.dtos;

import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.entities.Fiv;

import java.time.LocalDate;

public record TransferInitialDto(Long fivId,
                                 LocalDate date,
                                 String responsible,
                                 String farm) {

    public EmbryoTransfer toTransfer(Fiv fiv){
        return new EmbryoTransfer(
                fiv,
                date,
                responsible,
                farm
        );
    }

    public static TransferInitialDto toTransferDto(EmbryoTransfer embryoTransfer){
        return new TransferInitialDto(
                embryoTransfer.getFivTransfer().getId(),
                embryoTransfer.getDate(),
                embryoTransfer.getResponsible(),
                embryoTransfer.getFarm()
        );
    }
}
