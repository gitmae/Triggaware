package com.eastglade64.transformation.parsing;

import com.eastglade64.model.exception.ParseException;

public interface Parser<T> {
    T parse(String text) throws ParseException;
}
