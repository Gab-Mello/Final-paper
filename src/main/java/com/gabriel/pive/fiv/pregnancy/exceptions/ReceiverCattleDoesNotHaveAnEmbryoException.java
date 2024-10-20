package com.gabriel.pive.fiv.pregnancy.exceptions;

public class ReceiverCattleDoesNotHaveAnEmbryoException extends RuntimeException{

    public ReceiverCattleDoesNotHaveAnEmbryoException(){
        super("Esta receptora não está com um embrião.");
    }
}
