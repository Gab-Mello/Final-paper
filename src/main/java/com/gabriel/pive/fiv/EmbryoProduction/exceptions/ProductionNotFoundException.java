package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class ProductionNotFoundException extends RuntimeException{

    public ProductionNotFoundException(){
        super("Produção não encontrada.");
    }
}
