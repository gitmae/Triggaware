package com.eastglade64.model.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class TimeTest {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // TODO actual tests
    @Test
    public void justAnEyeTestForNow() {
        LocalDateTime jan1 = LocalDateTime.parse("2001-01-01 00:00:00.000", FORMAT);
        LocalDateTime jan123 = LocalDateTime.parse("2001-01-01 23:59:59.000", FORMAT);
        LocalDateTime jan2 = LocalDateTime.parse("2001-01-02 00:00:00.000", FORMAT);
        LocalDateTime jan223 = LocalDateTime.parse("2001-01-02 23:59:59.000", FORMAT);
        LocalDateTime jan3 = LocalDateTime.parse("2001-01-03 00:00:00.000", FORMAT);

        TimeInstant t0 = new TimeInstant(jan1);
        TimeInterval i01 = new TimeInterval(jan1, jan123);
        TimeInstant t1 = new TimeInstant(jan2);
        TimeInterval i12 = new TimeInterval(jan2, jan223);
        TimeInstant t2 = new TimeInstant(jan3);

        List<Time> times = Arrays.asList(t0, i01, t1, i12, t2);
        Collections.shuffle(times);

        System.out.println(t0 + " is before " + i01 + ": " + t0.isBefore(i01));
        System.out.println(t0 + " is after  " + i01 + ": " + t0.isAfter(i01));
        System.out.println(t1 + " is before " + i01 + ": " + t1.isBefore(i01));
        System.out.println(t1 + " is after  " + i01 + ": " + t1.isAfter(i01));

        for (Time t: new TreeSet<>(times)) {
            System.out.println(t);
        }
    }
}
