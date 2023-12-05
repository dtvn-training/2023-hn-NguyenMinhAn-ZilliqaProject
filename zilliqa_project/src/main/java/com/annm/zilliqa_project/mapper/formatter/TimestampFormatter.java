package com.annm.zilliqa_project.mapper.formatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimestampFormatter {
    public String Formatter(Double timestamp){
        long timestampInSeconds = Double.valueOf(timestamp).longValue();
        Instant instant = Instant.ofEpochSecond((Long) timestampInSeconds);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }
}
