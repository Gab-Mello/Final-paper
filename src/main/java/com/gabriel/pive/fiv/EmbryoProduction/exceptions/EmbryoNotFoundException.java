package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class EmbryoNotFoundException extends BusinessException {

    public EmbryoNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Embrião não encontrado.");
    }
}
