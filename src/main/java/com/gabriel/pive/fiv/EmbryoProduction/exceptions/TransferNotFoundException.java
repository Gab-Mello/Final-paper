package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class TransferNotFoundException extends BusinessException {

    public TransferNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Transferência de embriões não encontrada.");
    }
}
