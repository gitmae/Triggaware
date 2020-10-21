package com.eastglade64.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class TimeUtils {

    // Using the regular DateTimeFormatter.ofPattern breaks on patterns without separators
    // e.g.: yyyyMMddHHmmSS
    // also the absence of millis is a problem for LocalDateTime, this fixes both
    private static final DateTimeFormatter YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS_FORMAT =
            new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.YEAR, 4)
                    .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                    .appendValue(ChronoField.DAY_OF_MONTH, 2)
                    .appendValue(ChronoField.HOUR_OF_DAY, 2)
                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                    .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
                    .toFormatter();

    public static LocalDateTime parseYearMonthDayHourMinuteSeconds(String string) {
        return LocalDateTime.parse(string, YEAR_MONTH_DAY_HOUR_MINUTE_SECONDS_FORMAT);
    }
}
