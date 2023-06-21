package com.application;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class App {
    private boolean isLoggedIn = false;
    private Account currentUser;
    private Customer currentCustomer;
//    private final static CopyOnWriteArrayList<Bank> banks = new CopyOnWriteArrayList<>();
    private final static ArrayList<Bank> banks = new ArrayList<>();
//    List<String > abc = Collections.synchronizedList(new )
    private final static HashMap<Integer, Customer> customers = new HashMap<>();
    private Bank currentBank;
    private static final Scanner input = new Scanner(System.in);
    private boolean loginUser(){
        try{
            // GET AND FIND ACCOUNT NUMBER
            Account matchedAccount = currentBank.findAndGetAccount();

            if(matchedAccount == null)return false;

            // GET AND VERIFY PIN
            for(int i = 1; i <= 3; i++){
                try {
                    System.out.println("Enter Pin no: ");
                    int enteredPinNo = Integer.parseInt(input.nextLine());
                    if(!matchedAccount.isPin(enteredPinNo))throw new InvalidPinException();
                    else{
                        currentUser = matchedAccount;
                        System.out.println("Logged In Successfully");
                        return true;
                    }
                }catch(InvalidPinException error){
                    System.out.println(error.getMessage());
                }
            }
        }catch (Exception error){
            System.out.println("Unable to Login. Try Again Later...!!!");
        }
        return false;
    }
    private void logoutUser(){
        isLoggedIn = false;
        currentUser = null;
        currentBank = null;
    }
    private void createCustomer(){
        try{
            String firstName;
            String lastName;
            String address;
            String email;
            long phoneNo;

            while(true){
                System.out.println("Enter first name");
                firstName = input.nextLine();
                if(firstName.length() < 6){
                    System.out.println("First name should be more than 6 characters");
                }else break;
            }
            while(true){
                System.out.println("Enter last name");
                lastName = input.nextLine();
                if(lastName.length() < 6){
                    System.out.println("Last name should be more than 6 characters");
                }else break;
            }
            while(true){
                System.out.println("Enter email");
                email = input.nextLine();
                if(email.length() > 6)break;
            }
            while(true){
                System.out.println("Enter address");
                address = input.nextLine();
                if(address.length() < 6){
                    System.out.println("Address should be more than 6 characters");
                }else break;
            }
            while(true){
                System.out.println("Enter Phone Number");
                try{
                    phoneNo = Long.parseLong(input.nextLine());
                    if(Long.toString(phoneNo).length() == 10)break;
                    else throw new Exception();
                }catch (Exception err) {
                    System.out.println("invalid Phone Number");
                }
            }

            Customer customer = new Customer(firstName, lastName, email, address, phoneNo);
            customers.put(customer.getId(), customer);
            currentCustomer = customer;
            System.out.println("Customer created");
            System.out.println("----------------");
            System.out.println(customer);
            System.out.println();

        }catch (Exception err){
            System.out.println("unable to create customer");
        }
    }
    private boolean loginCustomer(){
        try{
            // GET AND FIND Customer object
            System.out.println("Enter your customer ID: ");
            System.out.print("> ");
            Customer matchedAccount = customers.get(Integer.parseInt(input.nextLine()));

            if(matchedAccount == null)return false;

            // GET AND VERIFY PIN
            for(int i = 1; i <= 3; i++){
                try {
                    System.out.println("Enter Phone no: ");
                    long enteredNo = Long.parseLong(input.nextLine());
                    if(!(matchedAccount.getPhoneNumber() == enteredNo))throw new InvalidPinException();
                    else{
                        currentCustomer = matchedAccount;
                        System.out.println("Customer Logged In Successfully");
                        return true;
                    }
                }catch(InvalidPinException error){
                    System.out.println(error.getMessage());
                }
            }
        }catch (Exception error){
            System.out.println("Unable to Login. Try Again Later...!!!");
        }
        return false;
    }
    private void selectBank(){
        while(true){
            System.out.println("List of Banks");
            if(banks.size() == 0){
                System.out.println("No bank found");
                System.out.println();
                return;
            }
            int i = 1;
            for(Bank bank : banks){
                System.out.println(i++ + ". " + bank.getName());
            }
            System.out.print(">");
            try{
                int index = Integer.parseInt(input.nextLine()) - 1;
                if(index < 0 || index > (banks.size()-1))throw new InvalidInputException();
                currentBank = banks.get(index);
                break;
            }catch (InvalidInputException |  NumberFormatException err){
                System.out.println(err.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("======================");
        System.out.println("Welcome to Application");
        System.out.println("======================");

        //===========================================================
        /* for the demo purpose I am creating a dummy bank objects */
        banks.add(new Bank("Indian Bank"));
        banks.add(new Bank("HDFC Bank"));
        banks.add(new Bank("Axis Bank"));
        //===========================================================

        App app = new App();
        int USER_INPUT;

        while(true) {
            System.out.println("---------App--------");
            System.out.println("1. Create Customer");
            System.out.println("2. Login as Customer");
            System.out.println("3. Select Bank");
            System.out.println("0. Exit Application");
            try{
                System.out.print("> ");
                USER_INPUT = Integer.parseInt(input.nextLine());
                if(USER_INPUT < 0 || USER_INPUT > 3)throw new InvalidInputException();

                if(USER_INPUT == 0)break;
                if(USER_INPUT == 1)app.createCustomer();
                if(USER_INPUT == 2)app.loginCustomer();
                else app.selectBank();

                if(app.currentBank!= null){
                    System.out.println("Selected Bank: " + app.currentBank.getName());
                }
                while(app.currentBank!= null){
                    if (app.currentCustomer == null){
                        System.out.println("create customer account to continue");
                        break;
                    }

                    System.out.println("---------"+ app.currentBank.getName() +"--------");
                    System.out.println("1. Create Account");
                    System.out.println("2. Login Account");
                    System.out.println("0. Select Different Bank");

                    try{
                        System.out.print("> ");
                        USER_INPUT = Integer.parseInt(input.nextLine());
                        if(USER_INPUT < 0 || USER_INPUT > 2)throw new InvalidInputException();
                    }catch (InvalidInputException error){
                        System.out.println(error.getMessage());
                    }
                    if(USER_INPUT == 0){
                        app.currentBank =null;
                        break;
                    }
                    else if (USER_INPUT == 2) {
                        app.isLoggedIn = app.loginUser();
                    }else{
                        app.currentUser = app.currentBank.createAccount(app.currentCustomer);
                    }

                    if(app.isLoggedIn){
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
                                app.logoutUser();
                                break;
                            }
                            switch (USER_INPUT){
                                case 1:{
                                    app.currentBank.deposit(app.currentUser);
                                    break;
                                }
                                case 2:{
                                    app.currentBank.withdraw(app.currentUser);
                                    break;
                                }
                                case 3:{

                                    app.currentBank.displayTransactions(app.currentUser);
                                    break;
                                }
                                case 4:{
                                    System.out.println();
                                    System.out.println("Account Details");
                                    System.out.println("---------------");
                                    System.out.println(app.currentUser);
                                    System.out.println();
                                    break;
                                }
                            }
                        }
                    }
                }
            }catch (InvalidInputException error){
                System.out.println(error.getMessage());
            }

        }


    }
}
