package com.application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.customExceptions.AccountNotFoundException;
import com.application.customExceptions.InSufficientBalanceException;
import com.application.customExceptions.NegativeAmountException;
import com.application.utils.DBConnectionUtils;

public class Bank {
    private final int id;
    private final String name;

     public Bank(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public final int getId() {
        return id;
    }
    public final String getName() {
        return name;
    }

    public final Account createAccount(int id, Customer customerDetails, int balance, int pin){

            //id -> Bank ID
            Account currentUser = new Account(id, customerDetails,balance, id, pin);
            customerDetails.addAccount(getName(), currentUser);
            return currentUser;

    }
    public final String withdraw(Account currentUser, int withdrawMoney){

        try{
            if(withdrawMoney < 0)throw new NegativeAmountException();
        }catch (NegativeAmountException err) {
            return err.getMessage();
        }
    

        if((currentUser.getBalance() - withdrawMoney) < 0){
            try{
                throw new InSufficientBalanceException();
            }catch (InSufficientBalanceException err){
            	return err.getMessage();
            }
        }else{
    		try {
    			Connection connection = DBConnectionUtils.getConnection();
    			String sql = "INSERT INTO transaction(accountId, balance, amount, type) \r\n"
    					+ "VALUES (?, ?, ?, ?);";
    			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    			statement.setInt(1, currentUser.getAccountNumber());
    			statement.setDouble(2, currentUser.getBalance() - withdrawMoney);
    			statement.setDouble(3, withdrawMoney);
    			statement.setString(4, "DEBITED");
    			statement.execute();
    			
    			ResultSet results = statement.getGeneratedKeys();
    			int transactionId = -1;
    			if(results.next()){
    				transactionId = results.getInt(1);
    			}
    			if(transactionId == -1)return "Something Went Wrong...!!!";
    			results.close();
    			statement.close();
    			updateAccount(currentUser.getAccountNumber(), currentUser.getBalance() - withdrawMoney);
    			currentUser.withdraw(transactionId, withdrawMoney, currentUser.getAccountNumber());
    		} catch (NegativeAmountException | SQLException e) {
    			// TODO Auto-generated catch block
    			return e.getMessage();
    		}
            return "Transaction Successful";
        }

    }
    public final String deposit(Account currentUser, int depositMoney, int accountNo){
        
        // GET AND FIND ACCOUNT NUMBER
        double receiveAccountBalance = isAccountAvailable(accountNo);
        Connection connection = DBConnectionUtils.getConnection();
        if(receiveAccountBalance == -1)return "No Account Found";

        
        try{
            if(depositMoney < 0)throw new NegativeAmountException();
        }catch (NegativeAmountException err) {
            return err.getMessage();
        }
        
        if(currentUser.getAccountNumber() == accountNo){
            try {
            	String sql = "INSERT INTO transaction(accountId, balance, amount, type) \r\n"
            			+ "VALUES (?, ?, ?, ?);";
            	PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            	statement.setInt(1, currentUser.getAccountNumber());
            	statement.setDouble(2, currentUser.getBalance() + depositMoney);
            	statement.setDouble(3, depositMoney);
            	statement.setString(4, "CREDITED");
                statement.execute();
                
                ResultSet results = statement.getGeneratedKeys();
                int transactionId = -1;
                if(results.next()){
                	transactionId = results.getInt(1);
                }
                if(transactionId == -1)return "Something Went Wrong...!!!";
                results.close();
                statement.close();
                updateAccount(accountNo, currentUser.getBalance() + depositMoney);
				currentUser.deposit(transactionId, depositMoney, currentUser.getAccountNumber());
			} catch (NegativeAmountException | SQLException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
            return "Transaction Successful";
        }else {
        	
        	if((currentUser.getBalance() - depositMoney) < 0){
        		try{
        			throw new InSufficientBalanceException();
        		}catch (InSufficientBalanceException err){
        			return err.getMessage();
        		}
        	}else{
        		try {
        			String sql = "INSERT INTO transaction(accountId, balance, amount, type) \r\n"
        					+ "VALUES (?, ?, ?, ?);";
        			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        			statement.setInt(1, currentUser.getAccountNumber());
        			statement.setDouble(2, currentUser.getBalance() - depositMoney);
        			statement.setDouble(3, depositMoney);
        			statement.setString(4, "DEBITED");
        			statement.execute();
        			
        			ResultSet results = statement.getGeneratedKeys();
        			int transactionId = -1;
        			if(results.next()){
        				transactionId = results.getInt(1);
        			}
        			if(transactionId == -1)return "Something Went Wrong...!!!";
        			results.close();
        			statement.close();
        			updateAccount(currentUser.getAccountNumber(), currentUser.getBalance() - depositMoney);
        			currentUser.withdraw(transactionId, depositMoney, accountNo);
        		} catch (NegativeAmountException | SQLException e) {
        			// TODO Auto-generated catch block
        			return e.getMessage();
        		}
        		try {
        			String sql = "INSERT INTO transaction(accountId, balance, amount, type) \r\n"
        					+ "VALUES (?, ?, ?, ?);";
        			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        			statement.setInt(1, accountNo);
        			statement.setDouble(2, receiveAccountBalance + depositMoney);
        			statement.setDouble(3, depositMoney);
        			statement.setString(4, "CREDITED");
        			statement.execute();
        			
        			ResultSet results = statement.getGeneratedKeys();
        			int transactionId = -1;
        			if(results.next()){
        				transactionId = results.getInt(1);
        			}
        			if(transactionId == -1)return "Something Went Wrong...!!!";
        			results.close();
        			statement.close();
        			updateAccount(accountNo, receiveAccountBalance + depositMoney);
        		} catch ( SQLException e) {
        			// TODO Auto-generated catch block
        			return e.getMessage();
        		}
        		return "Transaction Successful";
        	}
        }

    }

    private void updateAccount(int id, double amount) {
    	
    	Connection connection = DBConnectionUtils.getConnection();
    	String sql = "UPDATE account SET balance= ? WHERE id= ? ;";

        try {
        	PreparedStatement statement = connection.prepareStatement(sql);
        	statement.setDouble(1, amount);
        	statement.setInt(2, id);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private double isAccountAvailable(int accountNo){
    	String sql = "select balance from account where id="+ accountNo +" ;";
    	Connection connection = DBConnectionUtils.getConnection();
    	Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
	    	if(result.next()) {
	    		return result.getDouble("balance");
	    	}
		} catch (SQLException e) {
			
		}
    	 return -1;
    }
    public List<Transaction> displayTransactions(Account currentUser){
        return currentUser.getTransactions();
    }

}

