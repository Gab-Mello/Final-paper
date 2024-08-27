package com.gabriel.pive.fiv.cultivation.exceptions;

public class CultivationNotFoundException extends RuntimeException{

    public CultivationNotFoundException(){
        super("Cultivo não encontrado.");
    }
}
