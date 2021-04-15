package com.pierrick.kata.bank.functional;

import com.pierrick.kata.bank.technical.OperationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    private static OperationType operationType;
    private static LocalDateTime date;
    private static long amount;
    private static long balance;
    private static Operation operation;

    @BeforeAll
    public static void init() {
        // Given
        operationType = OperationType.DEPOSIT;
        date = LocalDateTime.now();
        amount = 10L;
        balance = 20L;

        // When
        operation = new Operation(operationType, date, amount, balance);
    }

    @Test
    void getType() {
        // Then
        assertEquals(operation.getType(), operationType);
    }

    @Test
    void getDate() {
        // Then
        assertEquals(operation.getDate(), date);
    }

    @Test
    void getAmount() {
        // Then
        assertEquals(operation.getAmount(), amount);
    }

    @Test
    void getBalance() {
        // Then
        assertEquals(operation.getBalance(), balance);
    }
}