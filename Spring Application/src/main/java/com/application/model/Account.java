package com.application.model;


import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account")
public class Account{
	
	@Transient
    private final String DEBITED = "DEBITED";
	@Transient
    private final String CREDITED = "CREDITED";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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

