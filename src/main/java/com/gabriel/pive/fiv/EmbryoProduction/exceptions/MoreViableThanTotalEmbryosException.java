package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class MoreViableThanTotalEmbryosException extends RuntimeException{

    public MoreViableThanTotalEmbryosException(){
        super("Número de viáveis maior que total.");
    }
}
