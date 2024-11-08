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

        // 使用 switch 表達式來簡化條件判斷
        localDateTime = switch (date.length()) {
            case 10 -> LocalDate.parse(date, dateOnlyFormatter).atStartOfDay(); // 日期部分
            default -> LocalDateTime.parse(date, fullFormatter); // 日期時間部分
        };

        // 轉換為 UTC 時間戳
        return localDateTime.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
    }
}
