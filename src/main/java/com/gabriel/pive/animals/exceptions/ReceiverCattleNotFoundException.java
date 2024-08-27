package com.gabriel.pive.animals.exceptions;

public class ReceiverCattleNotFoundException extends RuntimeException{

    public ReceiverCattleNotFoundException(){
        super("Receptora não encontrada.");
    }
    public ReceiverCattleNotFoundException(String message){
        super(message);
    }
}
