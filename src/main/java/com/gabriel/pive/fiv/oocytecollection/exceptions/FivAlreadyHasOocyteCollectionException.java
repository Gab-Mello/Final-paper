package com.gabriel.pive.fiv.oocytecollection.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class FivAlreadyHasOocyteCollectionException extends BusinessException {

    public FivAlreadyHasOocyteCollectionException(){
        super(HttpStatus.CONFLICT, "Esta fiv já possui uma coleta de oócitos registrada");
    }
}
