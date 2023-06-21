package com.application;

class CustomException{

    protected static void validateAmount(int amount) throws NegativeAmountException {
        if(amount < 0)throw new NegativeAmountException();

    }

}

class NegativeAmountException extends Exception{
    NegativeAmountException(){
        getMessage();
    }

    @Override
    public String getMessage() {
        return "Invalid Amount";
    }
}

class AccountNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Account not found";
    }
}

class InSufficientBalanceException extends Exception{

    @Override
    public String getMessage() {
        return "You don't have enough balance for this transaction";
    }
}

class InvalidPinException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid Pin Number";
    }
}

class InvalidInputException extends Exception{

    @Override
    public String getMessage() {
        return "Invalid input";
    }
}