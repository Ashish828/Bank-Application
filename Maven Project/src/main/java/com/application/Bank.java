package com.application;

import java.util.ArrayList;
import java.util.Scanner;

class Bank {
    private static int bankIdGenerator = 0;
    private static Scanner input = new Scanner(System.in);
    private final int id;
    private final String name;
    private final ArrayList<Account> accounts = new ArrayList<>();

     Bank(String name) {
        this.id = ++bankIdGenerator;
        this.name = name;
    }
    protected final int getId() {
        return id;
    }
    protected final String getName() {
        return name;
    }
    protected final Account createAccount(Customer customerDetails){
        try{
            int balance;
            int pin;

            while(true){
                System.out.println("Enter Credit amount");
                try{
                    balance = Integer.parseInt(input.nextLine());
                    if(balance > 0)break;
                    else throw new NegativeAmountException();
                }catch (NegativeAmountException err) {
                    System.out.println(err.getMessage());
                }
            }

            while(true){
                System.out.println("Enter 4 Digit Pin");
                try{
                    pin = Integer.parseInt(input.nextLine());
                    if(Integer.toString(pin).length() == 4)break;
                    else throw new InvalidPinException();
                }catch (InvalidPinException err) {
                    System.out.println(err.getMessage());
                }
            }

            //id -> Bank ID
            Account currentUser = new Account(  customerDetails,balance, id, pin);
            accounts.add(currentUser);

            System.out.println("Account created Successfully");
            System.out.println("Account Details");
            System.out.println("---------------");
            System.out.println(currentUser);
            return currentUser;
        }
        catch (Exception err){
            System.out.println("Unable to create account. Try again later...!!!");
        }
        // adding sample account for demo
        accounts.add(new Account(  customerDetails,10_000, id,  1234));
        return null;
    }
    protected void withdraw(Account currentUser){
        try{
            int withdrawMoney = 0;
            while(true){
                System.out.println("Enter Withdraw amount");
                try{
                    withdrawMoney = Integer.parseInt(input.nextLine());
                    if(withdrawMoney > 0)break;
                    else throw new NegativeAmountException();
                }catch (NegativeAmountException err) {
                    System.out.println(err.getMessage());
                }
            }

            if((currentUser.getBalance() - withdrawMoney) < 0){
                try{
                    throw new InSufficientBalanceException();
                }catch (InSufficientBalanceException err){
                    System.out.println(err.getMessage());
                }
            }else{
                currentUser.withdraw(withdrawMoney, currentUser.getAccountNumber());
                System.out.println("Transaction Successful");
            }
        }catch(Exception error){
            System.out.println("Something went wrong. Try again later...!!!");
        }
    }
    protected void deposit(Account currentUser){
        try{
            int deposit_money = 0;
            // GET AND FIND ACCOUNT NUMBER
            Account receiveAccount = findAndGetAccount();
            if(receiveAccount == null)return;

            while(true){
                System.out.println("Enter Deposit amount");
                try{
                    deposit_money = Integer.parseInt(input.nextLine());
                    if(deposit_money > 0)break;
                    else throw new NegativeAmountException();
                }catch (NegativeAmountException err) {
                    System.out.println(err.getMessage());
                }
            }
            if(currentUser.getAccountNumber() == receiveAccount.getAccountNumber()){
                currentUser.deposit(deposit_money, currentUser.getAccountNumber());
                System.out.println("Transaction Successful");
                return;
            }
            if((currentUser.getBalance() - deposit_money) < 0){
                try{
                    throw new InSufficientBalanceException();
                }catch (InSufficientBalanceException err){
                    System.out.println(err.getMessage());
                }
            }else{
                currentUser.withdraw(deposit_money, receiveAccount.getAccountNumber());
                receiveAccount.deposit(deposit_money, currentUser.getAccountNumber());
                System.out.println("Transaction Successful");
            }
        }catch (Exception error){
            System.out.println("Something went wrong. Please try again later...");
        }
    }
    protected Account findAndGetAccount(){
        Account matchedAccount = null;
        for(int i = 1; i <= 3; i++){
            try {
                System.out.println("Enter Account no: ");
                int enteredAccountNo = Integer.parseInt(input.nextLine());
                matchedAccount = isAccountAvailable(enteredAccountNo);
                if(matchedAccount != null)break;
                throw new AccountNotFoundException();

            }catch(AccountNotFoundException error){
                System.out.println(error.getMessage());
            }
        }
        return matchedAccount;
    }
    private Account isAccountAvailable(int accountNo){
        for(Account account: accounts){
            if(accountNo == account.getAccountNumber()){
                return account;
            }
        }
        return null;
    }
    protected void displayTransactions(Account currentUser){
        int i = 1;
        System.out.println("-------Transactions-------");
        if(currentUser.getTransactions().size() == 0) System.out.println("No Transactions available");
        for(Transaction transaction : currentUser.getTransactions()){
            System.out.println(i++ + ". " + transaction.toString());
        }
        System.out.println();
    }

}
