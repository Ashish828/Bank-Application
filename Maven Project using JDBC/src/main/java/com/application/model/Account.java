package com.application.model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.application.customExceptions.NegativeAmountException;
import com.application.utils.DBConnectionUtils;
public class Account{

    private final String DEBITED = "DEBITED";
    private final String CREDITED = "CREDITED";
    private final int id;
    private final int bankId;
    private double balance;
    private int pin;
    Customer customerDetails;
    private List<Transaction> transactions = new ArrayList<>();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Account(int id, Customer customerDetails, int balance, int bankId, int pin){
        this.id = id;
        this.customerDetails = customerDetails;
        this.balance = balance;
        this.pin = pin;
        this.bankId = bankId;
    }
    public int getAccountNumber() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isPin(int enteredPin) {
        return enteredPin == this.pin;
    }
    public List<Transaction> getTransactions() {
    	String sql = "select count(id) from transaction where accountId = "+ this.id+";";
    	Connection connection = DBConnectionUtils.getConnection();
    	int count = 0;
    	
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
	    	if(result.next()) {
	    		count = result.getInt(1);
	    	}
	    	
	    	result.close();
	    	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count != 0 || count != transactions.size()) {
			try {
				sql = "select * from transaction where accountId = "+ this.id+";";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				transactions = new ArrayList<>();
		    	while(result.next()) {
		    		create_transaction(result.getInt("id"),
		    				dtf.format(result.getTimestamp("date").toLocalDateTime()),
		    				result.getString("type"), result.getInt("accountId"),
		    				result.getInt("amount"), result.getDouble("balance"));
		    	}
		    	
		    	result.close();
		    	statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
        return transactions;
    }

    protected void deposit(int transactionID, int depositAmount, int accountNumber) throws NegativeAmountException {
        
        this.balance += depositAmount;
        create_transaction(transactionID, dtf.format(LocalDateTime.now()), CREDITED, accountNumber, depositAmount, this.balance);
    }

    protected void withdraw(int transactionID, int withdrawAmount, int accountNumber) throws NegativeAmountException {
        
        this.balance -= withdrawAmount;
        create_transaction(transactionID, dtf.format(LocalDateTime.now()), DEBITED, accountNumber, withdrawAmount, this.balance);
    }

    protected void create_transaction(int transactionID,String date_of_transaction, String transaction_type, int account_number, int transaction_amount, double current_balance) {
        Transaction transaction = new Transaction(transactionID, date_of_transaction, transaction_type, account_number, transaction_amount, current_balance);
        transactions.add(transaction);
    }
    
    @Override
    public String toString() {
        return "Account Number: " + id +
                ", User Name: " + customerDetails.getFirstName() +" " + customerDetails.getLastName() +
                ", Balance: " + balance +
                ", Address: " + customerDetails.getAddress() +
                ", Phone Number: " + customerDetails.getPhoneNumber();
    }
}

