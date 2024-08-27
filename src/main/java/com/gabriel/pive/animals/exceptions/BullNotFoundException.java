package com.gabriel.pive.animals.exceptions;

public class BullNotFoundException extends RuntimeException{

    public BullNotFoundException(){
        super("Touro não encontrado.");
    }
}
