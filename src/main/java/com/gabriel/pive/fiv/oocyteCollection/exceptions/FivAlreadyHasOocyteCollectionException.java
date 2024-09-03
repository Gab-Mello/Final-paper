package com.gabriel.pive.fiv.oocyteCollection.exceptions;

public class FivAlreadyHasOocyteCollectionException extends RuntimeException{

    public FivAlreadyHasOocyteCollectionException(){
        super("Esta fiv já possui uma coleta de oócitos registrada");
    }
}
