package com.gabriel.pive.fiv.embryoproduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class AllEmbryosAlreadyRegisteredException extends BusinessException {

    public AllEmbryosAlreadyRegisteredException(){
        super(HttpStatus.CONFLICT, "Todos os embriões desta produção já estão registrados.");
    }
}
