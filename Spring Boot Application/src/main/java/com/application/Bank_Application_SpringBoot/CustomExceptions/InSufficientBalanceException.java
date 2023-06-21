package com.application.Bank_Application_SpringBoot.CustomExceptions;

public class InSufficientBalanceException extends Exception{

    @Override
    public String getMessage() {
        return "You don't have enough balance for this transaction";
    }
}
