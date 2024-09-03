package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class MoreViableThanTotalEmbryosException extends RuntimeException{

    public MoreViableThanTotalEmbryosException(){
        super("Número de embriões viáveis é maior que o total.");
    }
}
