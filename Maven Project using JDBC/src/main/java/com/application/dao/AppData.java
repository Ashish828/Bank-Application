package com.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import com.application.model.*;
import com.application.utils.DBConnectionUtils;

public class AppData {
	public static Connection connection = null;
    private Account currentUser;
    private Customer currentCustomer;
    private final static ArrayList<Bank> banks = new ArrayList<>();
    private Bank currentBank;
    private static AppData app = null;
    
	public static AppData getInstance() {
    	if(app == null) {
    		app = new AppData();
    		
    		if(connection == null) {
            	String sql = "SELECT * FROM bank;";
            	connection = DBConnectionUtils.getConnection();
            	if(connection != null) {
            		try {
        				Statement statement = connection.createStatement();
        				ResultSet results = statement.executeQuery(sql);
        				while(results.next()) {
        					int id = results.getInt("id");
        					String name = results.getString("name");
        					banks.add(new Bank(id, name));
        				}
        				statement.close();
        				results.close();
        			} catch (SQLException e) {
        				
        			}
            		
            	}
    		}

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

	public static ArrayList<Bank> getBanks() {
		return banks;
	}
    
	/*-------------------------- Sign up and Log In Customer --------------------------*/
    public Boolean createCustomer(String firstName, String lastName, String address, String email, long phoneNo){

            try {
            	String sql = "INSERT INTO customer(first_name, last_name, email, address, phone_number) \r\n"
            			+ "VALUES (?, ?, ?, ?, ?);";
            	PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            	statement.setString(1, firstName);
            	statement.setString(2, lastName);
            	statement.setString(3, address);
            	statement.setString(4, email);
            	statement.setLong(5, phoneNo);
                statement.execute();
                
                ResultSet results = statement.getGeneratedKeys();
                int id = -1;
                if(results.next()){
                  id = results.getInt(1);
                }
                if(id == -1)return false;
                Customer customer = new Customer(id, firstName, lastName, email, address, phoneNo);
                currentCustomer = customer;
                results.close();
                statement.close();
            }catch(Exception err) {
            	System.out.println("===================");
            	System.out.println(err.getMessage());
            	System.out.println("=====================");
            	return false;
            }
            return true;
    }
    
    public boolean loginCustomer(int customerID, long phoneNumber){

    	try {
    		String sql = "select * from customer where id="+ customerID +" and phone_number="+ phoneNumber + ";";
        	Statement statement = connection.createStatement();
        	ResultSet result = statement.executeQuery(sql);
        	if(result.next()) {
        		Customer customer = new Customer(result.getInt("id"), 
        				result.getString("first_name"), result.getString("last_name"), 
        				result.getString("email"), result.getString("address"), result.getLong("phone_number"));
                currentCustomer = customer;
                return true;
        	}
        	return false;
    	}catch (Exception err) {
    		System.out.println("===================");
        	System.out.println(err.getMessage());
        	System.out.println("=====================");
        	return false;
		}
    }
    /*---------------------------------------------------------------------------------*/
    
    public Bank selectBank(String name) {
    	for(Bank bank : banks) {
    		if(bank.getName().contains(name))currentBank = bank;
    	}
    	return currentBank;
    }
    
    /*-------------------------- Sign up and Log In Account --------------------------*/
    public boolean createAccount(int amount, int pin) {
    	  try {
          	String sql = "INSERT INTO account(balance, pin, bankId, customerID) \r\n"
          			+ "VALUES (?, ?, ?, ?);";
          	PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          	statement.setInt(1, amount);
          	statement.setInt(2, pin);
          	statement.setInt(3, currentBank.getId());
          	statement.setInt(4, currentCustomer.getId());
            statement.execute();
              
	          ResultSet results = statement.getGeneratedKeys();
	          int id = -1;
	          if(results.next()){
	            id = results.getInt(1);
	          }
	          if(id == -1)return false;
	          
	      	  currentUser = currentBank.createAccount(id, currentCustomer, amount, pin);
	          results.close();
	          statement.close();
	          return true;
          }catch(Exception err) {
          	System.out.println("===================");
          	System.out.println(err.getMessage());
          	System.out.println("=====================");
          	return false;
          }
          
    }
    
    public boolean loginUser(int id, int pin){
    	try {
    		String sql = "select * from account where id="+ id +" and pin="+ pin + ";";
        	Statement statement = connection.createStatement();
        	ResultSet result = statement.executeQuery(sql);
        	if(result.next()) {
        		currentUser = currentBank.createAccount(id, currentCustomer, result.getInt("balance"), pin);
                return true;
        	}
        	return false;
    	}catch (Exception err) {
    		System.out.println("===================");
        	System.out.println(err.getMessage());
        	System.out.println("=====================");
        	return false;
		}
    }
    /*--------------------------------------------------------------------------------*/
    
    public static final Boolean isNewAccount(int bankID) {
    	try {
    		AppData app = AppData.getInstance();
    		String sql = "select * from account where customerID="+ app.currentCustomer.getId() +" and bankId="+ bankID + ";";
        	Statement statement = connection.createStatement();
        	ResultSet result = statement.executeQuery(sql);
        	if(result.next())return false;
        	return true;
    	}catch (Exception err) {
    		System.out.println("=====================");
        	System.out.println(err.getMessage());
        	System.out.println("=====================");
        	return true;
		}
	}
    
    public void logout() {
		currentUser = null;
		currentCustomer = null;
		currentBank = null;
	}

    
}
