package com.bank;

public abstract class Account {
    protected abstract void deposit(int deposit_amount, int account_number);

    protected abstract void withdraw(int withdraw_amount, int account_number);

    protected abstract void create_transaction(String date_of_transaction, String transaction_type, int account_number, int transaction_amount, double current_balance);

    protected abstract int getAccount_number();
}
