package org.barbatus.network.http.exception;

public class BarbatusHttpException extends Exception {

    public BarbatusHttpException(String message) {
        super(message);
    }

    public BarbatusHttpException(Exception exception) {
        super(exception);
    }

}
