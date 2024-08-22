package com.gabriel.pive.animals.exceptions;

public class RegistrationNumberAlreadyExistsException extends RuntimeException{

    public RegistrationNumberAlreadyExistsException(String message){
        super(message);
    }
}
