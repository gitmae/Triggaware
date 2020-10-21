package com.eastglade64.model.time;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Likely the best way of simulating sealed Traits
 * and pattern matching from Scala
 *
 * http://blog.higher-order.com/blog/2009/08/21/structural-pattern-matching-in-java/
 */
public abstract class Time implements Comparable<Time> {

    // package private constructor
    Time() {}

    public abstract boolean isBefore(Time time);
    public abstract boolean isAfter(Time time);

    @Override
    public int compareTo(Time time) {
        if (this.isBefore(time)) return -1;
        if (this.isAfter(time)) return 1;
        return 0;
    }

    public abstract <T> T match(Function<TimeInstant, T> tIns,
                                Function<TimeInterval, T> tInt);

}
