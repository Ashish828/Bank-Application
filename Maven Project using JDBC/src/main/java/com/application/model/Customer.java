package com.application.model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Customer {

    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;
    private HashMap<String, Account> accounts= new HashMap<>();

    public Customer(int id, String firstName, String lastName, String email, String address, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
    
    public void addAccount(String name, Account account) {
		accounts.put(name, account);
	}

    @Override
    public String toString() {
        return "ID: " + id +
                ", First Name: " + firstName+
                ", Last Name: " + lastName+
                ", Email: '" + email+
                ", Address: " + address +
                ", Phone no: " + phoneNumber;
    }
}

