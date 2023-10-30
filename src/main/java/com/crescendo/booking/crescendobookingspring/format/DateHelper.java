package com.crescendo.booking.crescendobookingspring.format;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateHelper {
    public static LocalTime convertTimestampToLocalTime(String timestamp) {
        long timestampL = Long.parseLong(timestamp);
        Instant instant = Instant.ofEpochMilli(timestampL);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toLocalTime();
    }
}
