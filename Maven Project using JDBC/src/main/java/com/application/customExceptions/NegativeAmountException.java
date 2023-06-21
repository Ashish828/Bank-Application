package com.application.customExceptions;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(){
        getMessage();
    }

    @Override
    public String getMessage() {
        return "Invalid Amount";
    }
}
