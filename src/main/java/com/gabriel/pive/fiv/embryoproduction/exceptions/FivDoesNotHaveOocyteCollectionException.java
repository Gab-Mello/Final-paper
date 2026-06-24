package com.gabriel.pive.fiv.embryoproduction.exceptions;

import com.gabriel.pive.infra.BusinessException;
import org.springframework.http.HttpStatus;

public class FivDoesNotHaveOocyteCollectionException extends BusinessException {

    public FivDoesNotHaveOocyteCollectionException(){
        super(HttpStatus.CONFLICT, "Esta fiv não possui uma coleta de oócitos ainda.");
    }
}
