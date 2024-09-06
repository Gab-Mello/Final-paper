package com.gabriel.pive.fiv.oocyteCollection.exceptions;

public class DonorAlreadyCollectedException extends RuntimeException{

    public DonorAlreadyCollectedException(){
        super("Esta doadora já foi coletada.");
    }
}
