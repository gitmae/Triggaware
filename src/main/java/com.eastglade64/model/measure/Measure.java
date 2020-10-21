package com.eastglade64.model.measure;

import com.eastglade64.model.time.Time;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Likely the best way of simulating sealed Traits
 * and pattern matching from Scala
 *
 * http://blog.higher-order.com/blog/2009/08/21/structural-pattern-matching-in-java/
 */
public abstract class Measure implements Comparable<Measure> {

    // package private constructor
    Measure() {}

    public abstract String getPod();
    public abstract Time getTime();

    public abstract <T> T match(Function<Registro, T> r,
                                Function<Curva, T> c);

    // not a fan of this name but calling it 'match'
    // causes problems of ambiguity with the method above
    public abstract void consume(Consumer<Registro> r,
                                 Consumer<Curva> c);
}
