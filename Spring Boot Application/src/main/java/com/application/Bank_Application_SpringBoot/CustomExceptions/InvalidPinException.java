package com.application.Bank_Application_SpringBoot.CustomExceptions;

public class InvalidPinException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid Pin Number";
    }
}