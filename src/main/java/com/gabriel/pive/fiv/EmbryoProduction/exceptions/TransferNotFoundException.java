package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class TransferNotFoundException extends RuntimeException{

    public TransferNotFoundException(){
        super("Transferência de embriões não encontrada.");
    }
}
