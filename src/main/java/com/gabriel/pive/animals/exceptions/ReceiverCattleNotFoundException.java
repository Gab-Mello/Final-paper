package com.gabriel.pive.animals.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class ReceiverCattleNotFoundException extends BusinessException {

    public ReceiverCattleNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Receptora não encontrada.");
    }
}
