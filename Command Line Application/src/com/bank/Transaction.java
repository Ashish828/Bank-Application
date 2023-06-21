package com.bank;
public class Transaction {
    private String date;
    private String type;
    private int amount;
    private double balance;
    private int account_no;

    public Transaction(String date, String type, int account_no, int amount, double balance) {
        this.date = date;
        this.type = type;
        this.account_no = account_no;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "Date=" + date +
                ", Type='" + type + '\'' +
                ", Account no=" + account_no +
                ", amount=" + amount +
                ", balance=" + balance;
    }
}
