package com.gabriel.pive.animals.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidDateException extends BusinessException {

    public InvalidDateException(){
        super(HttpStatus.CONFLICT, "Data inválida.");
    }
}
