package com.gabriel.pive.fiv.oocyteCollection.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class DonorAlreadyCollectedException extends BusinessException {

    public DonorAlreadyCollectedException(){
        super(HttpStatus.CONFLICT, "Esta doadora já foi coletada.");
    }
}
