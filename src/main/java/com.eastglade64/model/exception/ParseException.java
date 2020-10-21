package com.eastglade64.model.exception;

public class ParseException extends Exception  {

    public ParseException(String messaggio) {
        super(messaggio);
    }

    public ParseException(String messaggio, Exception cause) {
        super(messaggio, cause);
    }
}
