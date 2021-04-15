package com.pierrick.kata.bank.functional;

import com.pierrick.kata.bank.technical.DateUtils;
import com.pierrick.kata.bank.technical.OperationType;

import java.time.LocalDateTime;
import java.util.StringJoiner;


public class Operation {
    private static final String DELIMITER = " | ";

    private final OperationType type;
    private final LocalDateTime date;
    private final long amount;
    private final long balance;

    public Operation(OperationType operation, LocalDateTime date, long amount, long balance) {
        this.type = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return new StringJoiner(DELIMITER)
                .add(type.toString())
                .add(date.format(DateUtils.formatter))
                .add(String.valueOf(amount))
                .add(String.valueOf(balance))
                .toString();
    }
}
