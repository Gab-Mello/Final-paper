package com.gabriel.pive.animals.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class DonorCattleNotFoundException extends BusinessException {

    public DonorCattleNotFoundException(){
        super(HttpStatus.NOT_FOUND, "Doadora não encontrada");
    }
}
