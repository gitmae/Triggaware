package com.eastglade64.model.time;

import java.time.LocalDateTime;
import java.util.function.Function;

public final class TimeInstant extends Time {
    private final LocalDateTime instant;

    public TimeInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public LocalDateTime getInstant() {
        return instant;
    }

    @Override
    public boolean isBefore(Time time) {
        return time.match(
                instant -> this.instant.isBefore(instant.getInstant()),
                interval -> this.instant.isBefore(interval.getEnd())
        );
    }

    @Override
    public boolean isAfter(Time time) {
        return time.match(
                instant -> this.instant.isAfter(instant.getInstant()),
                interval -> this.instant.isAfter(interval.getStart())
        );
    }

    @Override
    public <T> T match(Function<TimeInstant, T> tIns,
                       Function<TimeInterval, T> tInt) {
        return tIns.apply(this);
    }

    @Override
    public String toString() {
        return instant.toString();
    }

}
