package com.eastglade64.model.exception;

public class TransformationException extends Exception  {

    public TransformationException(String messaggio) {
        super(messaggio);
    }

    public TransformationException(String messaggio, Exception cause) {
        super(messaggio, cause);
    }
}
