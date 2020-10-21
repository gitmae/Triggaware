package com.eastglade64.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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
}
