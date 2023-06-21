package com.application.Bank_Application_SpringBoot.CustomExceptions;

public class AccountNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Account not found";
    }
}
