package com.application.customExceptions;

public class CustomException{

    public static void validateAmount(int amount) throws NegativeAmountException {
        if(amount < 0)throw new NegativeAmountException();

    }
    public static void validatePin(int pin, int enteredPin)throws InvalidPinException{
    	if(pin != enteredPin)throw new InvalidPinException();
    }

}