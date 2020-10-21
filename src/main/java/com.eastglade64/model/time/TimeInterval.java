package com.eastglade64.model.time;


import java.time.LocalDateTime;
import java.util.function.Function;

public final class TimeInterval extends Time {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isBefore(Time time) {
        return time.match(
                instant -> this.start.isBefore(instant.getInstant()),
                interval -> this.start.isBefore(interval.getEnd())
        );
    }

    @Override
    public boolean isAfter(Time time) {
        return time.match(
                instant -> this.end.isAfter(instant.getInstant()),
                interval -> this.end.isAfter(interval.getStart())
        );
    }


    @Override
    public <T> T match(Function<TimeInstant, T> tIns,
                       Function<TimeInterval, T> tInt) {
        return tInt.apply(this);
    }


    @Override
    public String toString() {
        return "[" + start + " -> " + end + "]";
    }
}