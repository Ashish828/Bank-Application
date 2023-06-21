package com.application.customExceptions;

public class InvalidInputException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid input";
    }
}