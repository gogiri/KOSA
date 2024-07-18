package com.msa2024.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        return LocalDateTime.parse(dateString, formatter);
    }
}
