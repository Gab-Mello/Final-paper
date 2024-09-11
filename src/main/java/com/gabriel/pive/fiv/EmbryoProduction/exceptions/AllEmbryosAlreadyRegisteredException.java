package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class AllEmbryosAlreadyRegisteredException extends RuntimeException{

    public AllEmbryosAlreadyRegisteredException(){
        super("Todos os embriões desta produção já estão registrados.");
    }
}
