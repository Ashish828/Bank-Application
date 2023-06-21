package com.application.Bank_Application_SpringBoot.CustomExceptions;

public class InvalidInputException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid input";
    }
}