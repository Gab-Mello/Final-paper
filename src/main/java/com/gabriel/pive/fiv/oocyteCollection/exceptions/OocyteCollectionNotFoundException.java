package com.gabriel.pive.fiv.oocyteCollection.exceptions;

public class OocyteCollectionNotFoundException extends RuntimeException{

    public OocyteCollectionNotFoundException(){
        super("Coleta de oócitos não encontrada");
    }
}
