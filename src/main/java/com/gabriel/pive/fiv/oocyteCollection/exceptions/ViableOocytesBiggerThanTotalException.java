package com.gabriel.pive.fiv.oocyteCollection.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class ViableOocytesBiggerThanTotalException extends BusinessException {

    public ViableOocytesBiggerThanTotalException(){
        super(HttpStatus.CONFLICT, "Número de viáveis maior que total.");
    }
}
