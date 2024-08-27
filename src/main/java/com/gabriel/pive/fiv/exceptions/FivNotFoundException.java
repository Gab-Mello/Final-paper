package com.gabriel.pive.fiv.exceptions;

public class FivNotFoundException extends RuntimeException{

    public FivNotFoundException(){
        super("Fiv não encontrada.");
    }
}
