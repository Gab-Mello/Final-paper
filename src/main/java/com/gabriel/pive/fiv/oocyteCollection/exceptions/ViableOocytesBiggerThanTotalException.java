package com.gabriel.pive.fiv.oocyteCollection.exceptions;

public class ViableOocytesBiggerThanTotalException extends RuntimeException{

    public ViableOocytesBiggerThanTotalException(){
        super("Número de viáveis maior que total.");
    }
}
