package com.application.Bank_Application_SpringBoot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Bank_Application_SpringBoot.CustomExceptions.InSufficientBalanceException;
import com.application.Bank_Application_SpringBoot.CustomExceptions.InvalidPinException;
import com.application.Bank_Application_SpringBoot.CustomExceptions.NegativeAmountException;
import com.application.Bank_Application_SpringBoot.Model.Account;
import com.application.Bank_Application_SpringBoot.Model.Bank;
import com.application.Bank_Application_SpringBoot.Model.Customer;
import com.application.Bank_Application_SpringBoot.Repositories.AccountRepository;

@Service
public class AccountService {
	
	private static final String DEBITED = "DEBITED";
	private static final String CREDITED = "CREDITED";
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BankService bankService;
	@Autowired
	private TransactionService transactionService;
	
	public final int createAccount(int bankId, int customerId, int amount, int pin) {
	  try {
		  Bank bank = bankService.getBankById(bankId);
		  Customer customer = customerService.getCustomerById(customerId); 
		  
		  if(bank == null || customer == null || !isNewAccount(bankId, customerId))return -1;
	  	  Account newAccount = new Account(customer, bank, amount, pin);
	  	  
	  	  accountRepository.save(newAccount);
	  	  bank.getAccounts().add(newAccount);
	  	  customer.getAccounts().add(newAccount);
	  	  
	      return newAccount.getAccountNumber();
	  }
	  catch(Exception err) {
		  return -1;
	  }
	    
	}
  
	  public final boolean loginAccount(int id, int pin){
	  	try {
	  		Account account = findAccountById(id);
	      	if(account == null || !account.isPin(pin))return false;
	      	return true;
	  	}catch (Exception err) {
	      	return false;
		}
	  }
	  
	   public final String withdraw(int accountId, int amount, int pin) {
	    	try {
	    		Account account = findAccountById(accountId);
	    		
	    		if(!account.isPin(pin))throw new InvalidPinException();
	        	if(amount < 0)throw new  NegativeAmountException();
	        	if(amount > account.getBalance())throw new InSufficientBalanceException();
	        	
	        	return transactionService.createTransaction(account, DEBITED, amount) ? 
	        			"Transaction Successful" :
	        			"Transaction not Successful";
	    	}
	    	catch (InvalidPinException | NegativeAmountException | InSufficientBalanceException e) {
				return e.getMessage();
			}
	    }
	    
	    public final String deposit(int senderAccountId, int receiverAccountId, int amount, int pin) {
	    	try {
	    		Account senderAccount = findAccountById(senderAccountId);
	    		
	    		if(!senderAccount.isPin(pin))throw new InvalidPinException();
	        	if(amount < 0)throw new  NegativeAmountException();
	        	
	        	if(senderAccountId == receiverAccountId) {
	        		//deposit to same account
	        		return transactionService.createTransaction(senderAccount, CREDITED, amount)
    				? 
        			"Transaction Successful" :
        			"Transaction not Successful";
	        	}
	        	else {
	        		 
	        		Account receiverAccount = findAccountById(receiverAccountId);
	        		return (
	        				//withdraw from sender's account
	        				withdraw(senderAccountId, amount, pin).equals("Transaction Successful")
	        				&&
	        				//deposit to receiver's account
	        				transactionService.createTransaction(receiverAccount, CREDITED, amount)
	        				)? 
    	        			"Transaction Successful" :
    	        			"Transaction not Successful";
	        	} 
	    	}
	    	catch (InvalidPinException | NegativeAmountException e) {
				return e.getMessage();
			}
	    }

	    public final Boolean isNewAccount(int bankId, int customerId) {
	    	try {
	    		 Bank bank = bankService.getBankById(bankId);
	    		 Account userAccount = bank.getAccounts().stream()
	    		 	.filter(account -> account.getCustomerId() == customerId)
	    		 	.findFirst().get();
	    		return userAccount == null;
	    	}catch (Exception err) {
	        	return true;
			}
		}
	    
		public final Account findAccountById(int accountId) {
			return accountRepository.findById(accountId).orElse(null);
		}
 
}
