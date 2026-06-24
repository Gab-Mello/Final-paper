package com.gabriel.pive.animals.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class RegistrationNumberAlreadyExistsException extends BusinessException {

    public RegistrationNumberAlreadyExistsException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
