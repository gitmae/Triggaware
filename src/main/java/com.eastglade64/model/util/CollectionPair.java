package com.eastglade64.model.util;

import java.util.Collection;
import java.util.Map;

public class CollectionPair<T, C extends Collection<T>> {

    private final C _true;
    private final C _false;

    public CollectionPair(C _true, C _false) {
        this._true = _true;
        this._false = _false;
    }

    public CollectionPair(Map<Boolean, C> map) {
        this(map.get(true), map.get(false));
    }

    public C getTrue() {
        return _true;
    }

    public C getFalse() {
        return _false;
    }

}
