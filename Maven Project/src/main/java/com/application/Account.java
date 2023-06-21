package com.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
class Account{

    private final String DEBITED = "DEBITED";
    private final String CREDITED = "CREDITED";
    private static int accountIdGenerator = 0;
    private final int id;
    private final int bankId;
    private double balance;
    private int pin;
    Customer customerDetails;
    private final List<Transaction> transactions = new ArrayList<>();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    Account(Customer customerDetails, int balance, int bankId, int pin){
        this.id = ++accountIdGenerator;
        this.customerDetails = customerDetails;
        this.balance = balance;
        this.pin = pin;
        this.bankId = bankId;
    }
    protected int getAccountNumber() {
        return id;
    }

    protected double getBalance() {
        return balance;
    }

    protected boolean isPin(int enteredPin) {
        return enteredPin == this.pin;
    }
    protected List<Transaction> getTransactions() {
        return transactions;
    }

    protected void deposit(int depositAmount, int accountNumber) throws NegativeAmountException {
        CustomException.validateAmount(depositAmount);

        this.balance += depositAmount;
        create_transaction(dtf.format(LocalDateTime.now()), CREDITED, accountNumber, depositAmount, this.balance);
    }

    protected void withdraw(int withdrawAmount, int accountNumber) throws NegativeAmountException {
        CustomException.validateAmount(withdrawAmount);

        this.balance -= withdrawAmount;
        create_transaction(dtf.format(LocalDateTime.now()), DEBITED, accountNumber, withdrawAmount, this.balance);
    }

    protected void create_transaction(String date_of_transaction, String transaction_type, int account_number, int transaction_amount, double current_balance) {
        Transaction transaction = new Transaction(date_of_transaction, transaction_type, account_number, transaction_amount, current_balance);
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
