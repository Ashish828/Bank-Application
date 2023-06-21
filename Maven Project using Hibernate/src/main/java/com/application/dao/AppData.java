package com.application.dao;

import java.util.Set;

import com.application.customExceptions.InvalidPinException;
import com.application.customExceptions.NegativeAmountException;
import com.application.model.*;

public class AppData {
    private Account currentUser;
    private Customer currentCustomer;
    private static Set<Bank> banks;
    private Bank currentBank;
    private static AppData app = null;
    
	public static AppData getInstance() {
    	if(app == null) {
    		app = new AppData();
    		banks = BankRepository.getAllBanks();
    	}
    	
    	return app;
    }
    
    public Account getCurrentUser() {
		return currentUser;
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public Bank getCurrentBank() {
		return currentBank;
	}

	public static Set<Bank> getBanks() {
		return banks;
	}
    
	/*-------------------------- Sign up and Log In Customer --------------------------*/
    public final Boolean createCustomer(String firstName, String lastName, String address, String email, int phoneNo){

           currentCustomer = CustomerRepository.createCustomer(firstName, lastName, address, email, phoneNo);
           return currentCustomer != null;
    }
    
    public final boolean loginCustomer(int customerID, long phoneNumber){

    	try {
    		currentCustomer = CustomerRepository.loginCustomer(customerID, phoneNumber);
            return currentCustomer != null;
    	}catch (Exception err) {
    		
        	return false;
		}
    }
    /*---------------------------------------------------------------------------------*/
    
    public final Bank selectBank(String name) {
    	for(Bank bank : banks) {
    		if(bank.getName().contains(name))currentBank = bank;
    	}
    	return currentBank;
    }
    
    /*-------------------------- Sign up and Log In Account --------------------------*/
    public final boolean createAccount(int amount, int pin) {
    	  try {
	      	  currentUser = AccountRepository.createAccount(currentCustomer, currentBank, amount, pin);
	          return currentUser != null;
          }catch(Exception err) {
          	return false;
          }
          
    }
    
    public final boolean loginUser(int id, int pin){
    	try {
    		currentUser = AccountRepository.getAccount(id, pin, currentCustomer);
        	return currentUser != null;
    	}catch (Exception err) {
        	return false;
		}
    }
    
    public final Set<Transaction> displayTransactions(){
    	return AccountRepository.displayTransactions(currentUser);
    }
    
    public final String withdraw(int amount, int pin) {
    	try {
    		if(!currentUser.isPin(pin))throw new InvalidPinException();
        	if(amount <= 0)throw new  NegativeAmountException();
        	return AccountRepository.withdraw(currentUser, amount) ? 
        			"Transaction Successful" :
        			"Transaction not Successful";
    	}
    	catch (InvalidPinException | NegativeAmountException e) {
			return e.getMessage();
		}
    }
    
    public final String deposit(int amount, int pin, int receiveAccountNumber) {
    	try {
    		if(!currentUser.isPin(pin))throw new InvalidPinException();
        	if(amount <= 0)throw new  NegativeAmountException();
        	return AccountRepository.deposit(currentUser, receiveAccountNumber, amount) ? 
        			"Transaction Successful" :
        			"Transaction not Successful";
    	}
    	catch (InvalidPinException | NegativeAmountException e) {
			return e.getMessage();
		}
    }
    /*--------------------------------------------------------------------------------*/
    
    public final Boolean isNewAccount(int bankId) {
    	try {
    		return AccountRepository.getAccount(currentBank, currentCustomer) == null;
    	}catch (Exception err) {
        	return true;
		}
	}
    
    public final void logout() {
		currentUser = null;
		currentCustomer = null;
		currentBank = null;
		app = null;
	}

    
}
