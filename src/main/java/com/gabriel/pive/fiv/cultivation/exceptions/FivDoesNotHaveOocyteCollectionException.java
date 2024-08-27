package com.gabriel.pive.fiv.cultivation.exceptions;

public class FivDoesNotHaveOocyteCollectionException extends RuntimeException{

    public FivDoesNotHaveOocyteCollectionException(){
        super("Esta fiv não possui uma coleta de oócitos ainda.");
    }
}
