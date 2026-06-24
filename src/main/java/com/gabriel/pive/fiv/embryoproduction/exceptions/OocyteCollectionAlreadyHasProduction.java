package com.gabriel.pive.fiv.embryoproduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class OocyteCollectionAlreadyHasProduction extends BusinessException {

    public OocyteCollectionAlreadyHasProduction(){
        super(HttpStatus.CONFLICT, "Esta coleta já possui uma produção de embriões registrada.");
    }
}
