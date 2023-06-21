package com.application.model;

public class Transaction {
    private final int id;
    private final int amount;
    private final String type;
    private final String date;
    private final double balance;
    private final int account_no;

    Transaction(int transactionID, String date, String type, int account_no, int amount, double balance) {
        this.date = date;
        this.type = type;
        this.account_no = account_no;
        this.amount = amount;
        this.balance = balance;
        id = transactionID;
    }

    @Override
    public String toString() {
        return  "Transaction ID: " + id +
                ", Date: " + date +
                ", Type: " + type +
                ", Account no: " + account_no +
                ", amount: " + amount +
                ", balance: " + balance;
    }
}

