package com.gabriel.pive.fiv.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class FivNotFoundException extends BusinessException {

    public FivNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Fiv não encontrada.");
    }
}
