package com.pierrick.kata.bank.functional;

import com.pierrick.kata.bank.technical.AccountException;
import com.pierrick.kata.bank.technical.DateUtils;
import com.pierrick.kata.bank.technical.OperationType;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Operation> operations = new ArrayList<>();
    private final DateUtils dateUtils;

    private long balance = 0L;

    public Account(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) throws AccountException {
        if (amount <= 0L) {
            throw new AccountException("Deposit amount can't be lower or equal to zero");
        }

        saveOperation(OperationType.DEPOSIT, amount);
    }

    public void withdrawal(long amount) throws AccountException {
        if (amount <= 0L) {
            throw new AccountException("Deposit amount can't be lower or equal to zero");
        }

        if (amount > balance) {
            throw new AccountException("You don't any enough savings");
        }

        saveOperation(OperationType.WITHDRAWAL, -amount);
    }

    public void printOperations() {
        System.out.println(operations);
    }

    // Add synchronized to implement a thread-safe design
    private void saveOperation(OperationType type, long delta) {
        balance += delta;

        Operation operation = new Operation(
                type,
                dateUtils.getDateTime(),
                delta,
                balance
        );

        operations.add(operation);
    }
}
