package com.application;

class Transaction {
    private static int transactionIdGenerator = 0;
    private final int id;
    private final int amount;
    private final String type;
    private final String date;
    private final double balance;
    private final int account_no;

    Transaction(String date, String type, int account_no, int amount, double balance) {
        this.date = date;
        this.type = type;
        this.account_no = account_no;
        this.amount = amount;
        this.balance = balance;
        id = ++transactionIdGenerator;
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

