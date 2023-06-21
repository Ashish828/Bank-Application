package com.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private static int ACCCOUNT_NO_GENERATOR = 0;
    private static Scanner input = new Scanner(System.in);
    List<CurrentAccount> accounts = new ArrayList<CurrentAccount>();
    private boolean isLoggedIn = false;
    private CurrentAccount current_user;

    private void withdraw(){
        try{
            int withdraw_money = 0;
            while(true){
                System.out.println("Enter Withdraw amount");
                try{
                    withdraw_money = Integer.parseInt(input.nextLine());
                    if(withdraw_money > 0)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid amount");
                }
            }

            if((current_user.getBalance() - withdraw_money) < 0){
                System.out.println("Not enough balance...!!!");
            }else{
                current_user.withdraw(withdraw_money, current_user.getAccount_number());
                System.out.println("Transaction Successful");
            }
        }catch(Exception error){
            System.out.println("Something went wrong. Try again later...!!!");
        }
    }
    private void deposit(){
        try{
            int deposit_money = 0;
            // GET AND FIND ACCOUNT NUMBER
            CurrentAccount receive_account = find_and_get_account();
            if(receive_account == null)throw new Exception();

            while(true){
                System.out.println("Enter Deposit amount");
                try{
                    deposit_money = Integer.parseInt(input.nextLine());
                    if(deposit_money > 0)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid amount");
                }
            }
            if(current_user.getAccount_number() == receive_account.getAccount_number()){
                current_user.deposit(deposit_money, current_user.getAccount_number());
                System.out.println("Transaction Successful");
                return;
            }
            if((current_user.getBalance() - deposit_money) < 0){
                System.out.println("Not enough balance...!!!");
            }else{
                current_user.withdraw(deposit_money, receive_account.getAccount_number());
                receive_account.deposit(deposit_money, current_user.getAccount_number());
                System.out.println("Transaction Successful");
            }
        }catch (Exception error){
            System.out.println("Something went wrong. Please try again later...");
        }
    }
    private void displayTransactions(){
        int i = 1;
        System.out.println("-------Transactions-------");
        if(current_user.getTransactions().size() == 0) System.out.println("No Transactions available");
        for(Transaction transaction : current_user.getTransactions()){
            System.out.println(i++ + ". " + transaction.toString());
        }
        System.out.println();
    }
    private void logout_user(){
        isLoggedIn = false;
        current_user = null;
    }
    private CurrentAccount find_and_get_account(){
        CurrentAccount matched_account = null;
        for(int i = 1; i <= 3; i++){
            try {
                System.out.println("Enter Account no: ");
                int entered_account_no = Integer.parseInt(input.nextLine());
                matched_account = is_account_available(entered_account_no);
                if(matched_account != null)break;
                throw new Exception();

            }catch(Exception error){
                System.out.println("Invalid Account Number");
            }
        }
        return matched_account;
    }
    private boolean login_user(){
        try{
            Integer entered_pin_no =  null;

            // GET AND FIND ACCOUNT NUMBER
            CurrentAccount matched_account = find_and_get_account();

            if(matched_account == null)throw new Exception();

            // GET AND VERIFY PIN
            for(int i = 1; i <= 3; i++){
                try {
                    System.out.println("Enter Pin no: ");
                    entered_pin_no = Integer.parseInt(input.nextLine());
                    if(!matched_account.isPin(entered_pin_no))throw new Exception();
                    else{
                        current_user = matched_account;
                        System.out.println("Logged In Successfully");
                        return true;
                    }
                }catch(Exception error){
                    System.out.println("Invalid Pin Number");
                }
            }
        }catch (Exception error){
            System.out.println("Unable to Login. Try Again Later...!!!");
        }
        return false;
    }
    private void create_account(){

        try{
            int account_no = ++ACCCOUNT_NO_GENERATOR;
            String account_type = "CURRENT_ACCOUNT";
            List<Transaction> transactions = new ArrayList<>();
            String user_name;
            int balance;
            String address;
            long phone_no;
            int pin;

            while(true){
                System.out.println("Enter user name");
                user_name = input.nextLine();
                if(user_name.length() < 6){
                    System.out.println("user name should be more than 6 characters");
                }else break;
            }
            while(true){
                System.out.println("Enter Credit amount");
                try{
                    balance = Integer.parseInt(input.nextLine());
                    if(balance > 0)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid amount");
                }
            }

            while(true){
                System.out.println("Enter address");
                address = input.nextLine();
                if(user_name.length() < 6){
                    System.out.println("Address should be more than 6 characters");
                }else break;
            }

            while(true){
                System.out.println("Enter Phone Number");
                try{
                    phone_no = Long.parseLong(input.nextLine());
                    if(Long.toString(phone_no).length() == 10)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid Phone Number");
                }
            }

            while(true){
                System.out.println("Enter 4 Digit Pin");
                try{
                    pin = Integer.parseInt(input.nextLine());
                    if(Integer.toString(pin).length() == 4)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid Pin Number");
                }
            }
            current_user = new CurrentAccount( account_no,  user_name,
                     account_type,  balance,  address,
             phone_no,  pin, transactions);
            accounts.add(current_user);
            isLoggedIn = true;

            System.out.println("Account created Successfully");
            System.out.println("Account Details");
            System.out.println("---------------");
            System.out.println(current_user.toString());
        }
        catch (Exception err){
            System.out.println("Unable to create account. Try again later...!!!");
        }
        // adding sample account for demo
        accounts.add(new CurrentAccount( ++ACCCOUNT_NO_GENERATOR,  "Guest",
                "CURRENT_ACCOUNT",  10000,  "--------------",
                1234567890,  1234, new ArrayList<Transaction>()));
    }
    private CurrentAccount is_account_available(int account_no){
        for(CurrentAccount account: accounts){
            if(account_no == account.getAccount_number()){
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("================");
        System.out.println("Welcome to bank");
        System.out.println("================");
        Bank bank = new Bank();
        int USER_INPUT = 0;

        while(true){
            System.out.println("---------Bank--------");
            System.out.println("1. Create Account");
            System.out.println("2. Login Account");
            System.out.println("0. Exit Application");

            try{
                System.out.print("> ");
                USER_INPUT = Integer.parseInt(input.nextLine());
                if(USER_INPUT < 0 || USER_INPUT > 2)throw new Exception();
            }catch (Exception error){
                System.out.println("Invalid Input");
            }
            if(USER_INPUT == 0)break;
            else if (USER_INPUT == 2) {
                bank.isLoggedIn = bank.login_user();
            }else{
                bank.create_account();
            }

            if(bank.isLoggedIn){
                while(true){
                    System.out.println("---------My Account--------");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Show Transactions");
                    System.out.println("4. Account Details");
                    System.out.println("0. Logout");

                    try{
                        System.out.print("> ");
                        USER_INPUT = Integer.parseInt(input.nextLine());
                        if(USER_INPUT < 0 || USER_INPUT > 4)throw new Exception();
                    }catch (Exception error){
                        System.out.println("Invalid Input");
                    }

                    if(USER_INPUT == 0){
                        bank.logout_user();
                        break;
                    }
                    switch (USER_INPUT){
                        case 1:{
                            bank.deposit();
                            break;
                        }
                        case 2:{
                            bank.withdraw();
                            break;
                        }
                        case 3:{

                            bank.displayTransactions();
                            break;
                        }
                        case 4:{
                            System.out.println();
                            System.out.println("Account Details");
                            System.out.println("---------------");
                            System.out.println(bank.current_user);
                            System.out.println();
                            break;
                        }
                        default:
                            continue;
                    }
                }
            }
        }
        input.close();
    }
}
