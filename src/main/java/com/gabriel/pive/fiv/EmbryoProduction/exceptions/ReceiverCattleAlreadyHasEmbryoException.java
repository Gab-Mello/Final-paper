package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class ReceiverCattleAlreadyHasEmbryoException extends BusinessException {

    public ReceiverCattleAlreadyHasEmbryoException(){
        super(HttpStatus.CONFLICT, "Esta receptora já possui um embrião.");
    }
}
