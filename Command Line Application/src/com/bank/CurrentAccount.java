package com.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CurrentAccount extends Account{

    private int account_number;
    private String user_name;
    private String account_type;
    private double balance;
    private String address;
    private long phone_number;
    private int pin;
    private List<Transaction> transactions;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    protected CurrentAccount(int account_number, String user_name,
                          String account_type, int balance, String address,
                          long phone_number, int pin, List<Transaction> transactions)
    {
        this.account_number = account_number;
        this.user_name = user_name;
        this.account_type = account_type;
        this.balance = balance;
        this.address = address;
        this.phone_number = phone_number;
        this.pin = pin;
        this.transactions = transactions;
    }

    protected int getAccount_number() {
        return account_number;
    }

    protected double getBalance() {
        return balance;
    }

    protected boolean isPin(int entered_pin) {
        return entered_pin == this.pin;
    }

    protected List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    protected void deposit(int deposit_amount, int account_number) {
        this.balance += deposit_amount;
        create_transaction(dtf.format(LocalDateTime.now()), "Credited", account_number, deposit_amount, this.balance);
    }

    @Override
    protected void withdraw(int withdraw_amount, int account_number) {
        this.balance -= withdraw_amount;
        create_transaction(dtf.format(LocalDateTime.now()), "Debited", account_number, withdraw_amount, this.balance);
    }

    @Override
    protected void create_transaction(String date_of_transaction, String transaction_type, int account_number, int transaction_amount, double current_balance) {
        Transaction transaction = new Transaction(date_of_transaction, transaction_type, account_number, transaction_amount, current_balance);
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "Account Number=" + account_number +
                ", User Name='" + user_name + '\'' +
                ", Account Type='" + account_type + '\'' +
                ", Balance=" + balance +
                ", Address='" + address + '\'' +
                ", Phone Number=" + phone_number;
    }
}
