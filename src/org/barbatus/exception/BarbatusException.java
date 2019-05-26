package org.barbatus.exception;

public class BarbatusException extends Exception {

    public BarbatusException(String message) {
        super(message);
    }

    public BarbatusException(Exception exception) {
        super(exception);
    }

}
