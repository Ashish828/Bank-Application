package com.application.Bank_Application_SpringBoot.Model;


import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account{
	
	@Transient
    private final String DEBITED = "DEBITED";
	@Transient
    private final String CREDITED = "CREDITED";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
	@ManyToOne
	@JoinColumn(name = "bank_id")
	@JsonBackReference
    private Bank bank;
    private double balance;
    private int pin;
    @ManyToOne
	@JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customerDetails;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Transaction> transactions = new HashSet<>();
    @Transient
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Account(Customer customerDetails, Bank bank, int balance, int pin){
        this.customerDetails = customerDetails;
        this.balance = balance;
        this.pin = pin;
        this.bank = bank;
    }
    
    public Account(){
    }
    
    public int getAccountNumber() {
        return id;
    } 

    public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCustomerId() {
		return customerDetails.getId();
	}
	public Set<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return this.transactions;
	}
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	public boolean isPin(int enteredPin) {
        return enteredPin == this.pin;
    }

    @Override
    public String toString() {
        return "Account Number: " + id +
                ", User Name: " + customerDetails.getFirstName() +" " + customerDetails.getLastName() +
                ", Balance: " + balance +
                ", Address: " + customerDetails.getAddress();
    }

}

