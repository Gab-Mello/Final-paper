package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductionNotFoundException extends BusinessException {

    public ProductionNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Produção não encontrada.");
    }
}
