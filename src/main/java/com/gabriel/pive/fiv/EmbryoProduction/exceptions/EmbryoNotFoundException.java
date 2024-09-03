package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class EmbryoNotFoundException extends RuntimeException{

    public EmbryoNotFoundException(){
        super("Embrião não encontrado.");
    }
}
