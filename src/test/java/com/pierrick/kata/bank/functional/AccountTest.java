package com.pierrick.kata.bank.functional;

import com.pierrick.kata.bank.technical.AccountException;
import com.pierrick.kata.bank.technical.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    private DateUtils dateUtils;

    @Test
    void getBalance_shouldReturnZeroIfNoOperations() {
        // When
        Account account = new Account(dateUtils);

        // Then
        assertEquals(0L, account.getBalance());
    }

    @Test
    void deposit_shouldThrowIfAmountIsIncorrect() {
        // Given
        long amount = -20L;

        // When
        Account account = new Account(dateUtils);

        // Then
        assertThrows(AccountException.class, () -> account.deposit(amount));
    }

    @Test
    void deposit_shouldSaveOperationAndUpdateBalance() {
        // Given
        long amount = 20L;

        // When
        Account account = new Account(dateUtils);
        when(dateUtils.getDateTime()).thenReturn(LocalDateTime.now());

        // Then
        assertDoesNotThrow(() -> account.deposit(amount));
        assertEquals(amount, account.getBalance());
    }

    @Test
    void withdrawal_shouldThrowIfAmountIsIncorrect() {
        // Given
        long amount = -20L;

        // When
        Account account = new Account(dateUtils);

        // Then
        assertThrows(AccountException.class, () -> account.withdrawal(amount));
    }

    @Test
    void withdrawal_shouldThrowIfNotEnoughSavings() {
        // Given
        long amount = 20L;

        // When
        Account account = new Account(dateUtils);

        // Then
        assertThrows(AccountException.class, () -> account.withdrawal(amount));
    }

    @Test
    void withdrawal_shouldSaveOperationAndUpdateBalance() throws AccountException {
        // Given
        long previousBalance = 60L;
        long amount = 20L;
        long expectedBalance = 40L;

        // When
        Account account = new Account(dateUtils);
        when(dateUtils.getDateTime()).thenReturn(LocalDateTime.of(2021, 4, 15, 16, 33));
        account.deposit(previousBalance);
        when(dateUtils.getDateTime()).thenReturn(LocalDateTime.of(2021, 4, 15, 17, 33));


        // Then
        assertDoesNotThrow(() -> account.withdrawal(amount));
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void printOperations() throws AccountException {
        // Given
        long amountDeposit = 50L;
        long amountWithdrawal = 20L;

        // When
        System.setOut(new PrintStream(outContent));
        Account account = new Account(dateUtils);
        when(dateUtils.getDateTime()).thenReturn(LocalDateTime.of(2021, 4, 15, 16, 33));
        account.deposit(amountDeposit);
        when(dateUtils.getDateTime()).thenReturn(LocalDateTime.of(2021, 4, 15, 17, 33));
        account.withdrawal(amountWithdrawal);
        account.printOperations();

        // Then
        assertTrue(outContent.toString().contains("DEPOSIT | 15-04-2021 16:33 | 50 | 50"));
        assertTrue(outContent.toString().contains("WITHDRAWAL | 15-04-2021 17:33 | -20 | 30"));
    }
}