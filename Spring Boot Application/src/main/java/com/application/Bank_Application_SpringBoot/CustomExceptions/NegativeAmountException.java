package com.application.Bank_Application_SpringBoot.CustomExceptions;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(){
        getMessage();
    }

    @Override
    public String getMessage() {
        return "Invalid Amount";
    }
}
