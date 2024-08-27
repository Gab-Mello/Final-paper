package com.gabriel.pive.fiv.cultivation.exceptions;

public class ReceiverCattleAlreadyHasEmbryoException extends RuntimeException{

    public ReceiverCattleAlreadyHasEmbryoException(){
        super("Esta receptora já possui um embrião.");
    }
}
