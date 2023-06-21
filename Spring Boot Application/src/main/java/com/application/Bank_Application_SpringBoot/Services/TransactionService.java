package com.application.Bank_Application_SpringBoot.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Bank_Application_SpringBoot.Model.Account;
import com.application.Bank_Application_SpringBoot.Model.Transaction;
import com.application.Bank_Application_SpringBoot.Repositories.AccountRepository;
import com.application.Bank_Application_SpringBoot.Repositories.TransactionRepository;

@Service
public class TransactionService {
	
	private static final String DEBITED = "DEBITED";
	
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;

	public boolean createTransaction(Account currentAccount, String type, int transactionAmount) {
		try {
			double balance = type.equals(DEBITED) ? 
					currentAccount.getBalance() - transactionAmount
					: 
					currentAccount.getBalance() + transactionAmount
					;
			Transaction transaction = new Transaction(
					DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()),
					type,
					currentAccount, 
					transactionAmount, 
					balance
					);
			
			transactionRepository.save(transaction);
			
			currentAccount.getTransactions().add(transaction);
			currentAccount.setBalance(balance);
			accountRepository.save(currentAccount);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
    public final Set<Transaction> getAllTransactions(int accountId){
    	Account account = accountRepository.findById(accountId).orElse(null);
    	if(account == null) return null;
    	return account.getTransactions();
    }
    
}
