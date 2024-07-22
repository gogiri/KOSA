package com.msa2024.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    // 날짜 및 시간 형식 상수를 정의
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // DateTimeFormatter를 사용하여 상수를 기반으로 한 포맷터를 정의
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * 현재 날짜와 시간을 지정된 형식으로 문자열로 반환
     * @return 현재 날짜와 시간 문자열.
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(formatter);
    }


//    public static String formatDate(LocalDateTime dateTime) {
//        return dateTime.format(formatter);
//    }
//
//    public static LocalDateTime parseDate(String dateString) throws DateTimeParseException {
//        return LocalDateTime.parse(dateString, formatter);
//    }
}
