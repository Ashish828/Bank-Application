package com.application.customExceptions;

public class InvalidPinException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid Pin Number";
    }
}