package com.gabriel.pive.animals.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class BullNotFoundException extends BusinessException {

    public BullNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Touro não encontrado.");
    }
}
