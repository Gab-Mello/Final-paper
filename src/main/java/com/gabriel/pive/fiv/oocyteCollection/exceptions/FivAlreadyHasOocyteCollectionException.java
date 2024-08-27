package com.gabriel.pive.fiv.oocyteCollection.exceptions;

import com.gabriel.pive.fiv.cultivation.exceptions.FivAlreadyHasCultivation;

public class FivAlreadyHasOocyteCollectionException extends RuntimeException{

    public FivAlreadyHasOocyteCollectionException(){
        super("Esta fiv já possui uma coleta de oócitos registrada");
    }
}
