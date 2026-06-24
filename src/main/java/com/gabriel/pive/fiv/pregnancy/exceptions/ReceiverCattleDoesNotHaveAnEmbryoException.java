package com.gabriel.pive.fiv.pregnancy.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class ReceiverCattleDoesNotHaveAnEmbryoException extends BusinessException {

    public ReceiverCattleDoesNotHaveAnEmbryoException(){
        super(HttpStatus.CONFLICT, "Esta receptora não está com um embrião.");
    }
}
