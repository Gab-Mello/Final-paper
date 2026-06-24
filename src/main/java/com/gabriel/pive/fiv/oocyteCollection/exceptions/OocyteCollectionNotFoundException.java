package com.gabriel.pive.fiv.oocyteCollection.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class OocyteCollectionNotFoundException extends BusinessException {

    public OocyteCollectionNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Coleta de oócitos não encontrada");
    }
}
