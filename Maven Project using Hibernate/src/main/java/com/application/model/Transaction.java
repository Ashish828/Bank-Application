package com.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private int amount;
    private String type;
    private String date;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(String date, String type, Account account, int amount, double balance) {
        this.date = date;
        this.type = type;
        this.account = account;
        this.amount = amount;
        this.balance = balance;
    }
    
    public Transaction() {
    }

    @Override
    public String toString() {
        return  "Transaction ID: " + id +
                ", Date: " + date +
                ", Type: " + type +
                ", Account no: " + account.getAccountNumber() +
                ", amount: " + amount +
                ", balance: " + balance;
    }
}

