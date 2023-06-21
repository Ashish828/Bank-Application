package com.application.customExceptions;

public class InSufficientBalanceException extends Exception{

    @Override
    public String getMessage() {
        return "You don't have enough balance for this transaction";
    }
}
