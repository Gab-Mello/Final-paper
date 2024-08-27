package com.gabriel.pive.fiv.cultivation.exceptions;

public class EmbryoNotFoundException extends RuntimeException{

    public EmbryoNotFoundException(){
        super("Embrião não encontrado.");
    }
}
