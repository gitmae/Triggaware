package com.eastglade64.util;

import com.eastglade64.model.util.CollectionPair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T> List<T> sorted(Collection<T> collection, Comparator<T> comparator) {
        List<T> sorted = new ArrayList<>(collection);
        sorted.sort(comparator);
        return sorted;
    }


    public static String mkString(String separator, Collection<?> collection) {
        return collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining(separator));
    }

    // Collectors.partitioningBy( n -> n < 0)
    public static <T, C extends Collection<T>> CollectionPair<T, List<T>> span(C collection, Function<T, Boolean> predicate) {
        return new CollectionPair<>(
                collection.stream().collect(Collectors.groupingBy(predicate)));

    }
}
