package com.gabriel.pive.animals.exceptions;

public class DonorCattleNotFoundException extends RuntimeException{

    public DonorCattleNotFoundException(){
        super("Doadora não encontrada");
    }
}
