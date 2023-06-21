package com.application.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.application.model.Account;
import com.application.model.Bank;
import com.application.model.Customer;
import com.application.utils.HibernateConnectionUtils;

class AccountRepository {
	
    private static final String DEBITED = "DEBITED";
    private static final String CREDITED = "CREDITED";
    
	static final Account createAccount(Customer currentCustomer, Bank currentBank, int amount, int pin) {
		 try {
        	Session session = HibernateConnectionUtils.getHibernateSession();
        	Account newAccount = new Account(currentCustomer, currentBank, amount, pin);
        	Transaction transaction = session.beginTransaction();
        	currentCustomer.getAccounts().add(newAccount);
        	currentBank.getAccounts().add(newAccount);
        	session.save(newAccount);
        	transaction.commit();
        	HibernateConnectionUtils.closeHibernateSession(session);
        	
        	return newAccount;
        	
        }catch(Exception err) {
        	return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	static final Account getAccount(int accountID, int pin, Customer customer) {
		try {
			
			Session session = HibernateConnectionUtils.getHibernateSession();
			String hql = "from Account where customerDetails.id = :custID and id = :accountID";
			Query query = session.createQuery(hql);
			query.setParameter("custID", customer.getId());
			query.setParameter("accountID", accountID);
			List<Account> account = query.list();
			HibernateConnectionUtils.closeHibernateSession(session);
			return account.size() > 0 ? 
					account.get(0).isPin(pin) ?
							account.get(0) : 
							null
					: null;
		}catch (Exception err) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	static final Account getAccount(Bank bank, Customer customer) {
		try {
			
			Session session = HibernateConnectionUtils.getHibernateSession();
			String hql = "from Account where customerDetails.id = :custID and bank.id = :bankID";
			Query query = session.createQuery(hql);
			query.setParameter("custID", customer.getId());
			query.setParameter("bankID", bank.getId());
			List<Account> account = query.list();
			HibernateConnectionUtils.closeHibernateSession(session);
			return account.size() > 0 ? account.get(0) : null;
		}catch (Exception err) {
			return null;
		}
	}

	static final Set<com.application.model.Transaction> displayTransactions(Account currentUser){
		return currentUser.getTransactions();
	}
	
	static final boolean withdraw(Account currentAccount, int withdrawAmount) {
		try {
			double currentBalance = currentAccount.getBalance();
			if(currentBalance < withdrawAmount)return false;
			currentAccount.setBalance(currentBalance - withdrawAmount);
			com.application.model.Transaction accountTtransaction = 
					new com.application.model.Transaction(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()), 
														  DEBITED, 
														  currentAccount, 
														  withdrawAmount, 
														  currentBalance - withdrawAmount);
			Session session = HibernateConnectionUtils.getHibernateSession();
        	Transaction transaction = session.beginTransaction();
        	currentAccount.getTransactions().add(accountTtransaction);
        	session.save(accountTtransaction);
        	transaction.commit();
        	HibernateConnectionUtils.closeHibernateSession(session);
        	
        	Session session2 = HibernateConnectionUtils.getHibernateSession();
        	Transaction transaction2 = session2.beginTransaction();
        	session2.update(currentAccount);
        	transaction2.commit();
        	HibernateConnectionUtils.closeHibernateSession(session2);
	        return true;
		}catch(Exception err) {
			return false;
		}
	}
	
	static final boolean deposit(Account currentAccount, int receiveAccountNumber, int depositAmount) {
		try {
			if(currentAccount.getAccountNumber() == receiveAccountNumber) {
				// deposit to current account
				currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
				com.application.model.Transaction accountTtransaction = 
						new com.application.model.Transaction(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()), 
															  CREDITED, 
															  currentAccount, 
															  depositAmount, 
															  currentAccount.getBalance());
				Session session = HibernateConnectionUtils.getHibernateSession();
	        	Transaction transaction = session.beginTransaction();
	        	currentAccount.getTransactions().add(accountTtransaction);
	        	session.save(accountTtransaction);
	        	transaction.commit();
	        	HibernateConnectionUtils.closeHibernateSession(session);
	        	
	        	Session session2 = HibernateConnectionUtils.getHibernateSession();
	        	Transaction transaction2 = session2.beginTransaction();
	        	session2.update(currentAccount);
	        	transaction2.commit();
	        	HibernateConnectionUtils.closeHibernateSession(session2);
	        	return true;
			}else {
				//withdraw from current account
				Account receiveAccount = findAccount(receiveAccountNumber);
				if(receiveAccount != null && withdraw(currentAccount, depositAmount)) {
					// deposit to receiver account
					deposit(receiveAccount, receiveAccountNumber, depositAmount);
					return true;
				}
				else return false;
			}
		}catch(Exception err) {
			return false;
		}
	}
	
	static final Account findAccount(int accountNumber) {
		Session session = HibernateConnectionUtils.getHibernateSession();
    	Transaction transaction = session.beginTransaction();
    	Account account = (Account)session.get(Account.class, accountNumber);
    	transaction.commit();
    	HibernateConnectionUtils.closeHibernateSession(session);
    	return account;
	}
}










