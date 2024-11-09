package com.example.cathayunited.common;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class ChangeDateUtil {

    public Long stringChangeLong(String date) {

        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDateTime;

        localDateTime = switch (date.length()) {
            case 10 -> LocalDate.parse(date, dateOnlyFormatter).atStartOfDay(); // 日期部分
            default -> LocalDateTime.parse(date, fullFormatter); // 日期時間部分
        };
        return localDateTime.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public Long getStartOfDayTimestamp(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDateTime startOfDay = null;

        try {
            startOfDay = LocalDateTime.parse(dateStr, dateTimeFormatter);
        } catch (Exception e1) {
            try {
                LocalDate date = LocalDate.parse(dateStr, dateOnlyFormatter);
                startOfDay = date.atStartOfDay();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        if (startOfDay != null) {
            return startOfDay.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        }
        return null;
    }

    public Long getEndOfDayTimestamp(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDateTime endOfDay = null;

        try {
            endOfDay = LocalDateTime.parse(dateStr, dateTimeFormatter);
            endOfDay = endOfDay.toLocalDate().atTime(23, 59, 59, 999999999);
        } catch (Exception e1) {
            try {
                LocalDate date = LocalDate.parse(dateStr, dateOnlyFormatter);
                endOfDay = date.atTime(23, 59, 59, 999999999);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        if (endOfDay != null) {
            return endOfDay.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        }
        return null;
    }
}