package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class FivAlreadyHasCultivation extends RuntimeException{

    public FivAlreadyHasCultivation(){
        super("Esta fiv já possui um cultivo registrado.");
    }
}
