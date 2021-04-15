package com.pierrick.kata.bank.technical;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}
