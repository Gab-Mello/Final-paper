package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class FivDoesNotHaveOocyteCollectionException extends RuntimeException{

    public FivDoesNotHaveOocyteCollectionException(){
        super("Esta fiv não possui uma coleta de oócitos ainda.");
    }
}
