package com.gabriel.pive.animals.exceptions;

public class InvalidDateException extends RuntimeException{

    public InvalidDateException(){
        super("Data inválida.");
    }
}
