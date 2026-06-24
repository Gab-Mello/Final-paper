package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidNumberOfEmbryosException extends BusinessException {

    public InvalidNumberOfEmbryosException(){
        super(HttpStatus.BAD_REQUEST, "Número inválido!");
    }
}
