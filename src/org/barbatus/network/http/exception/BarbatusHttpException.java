package org.barbatus.network.http.exception;

import org.barbatus.exception.BarbatusException;

public class BarbatusHttpException extends BarbatusException {

    public BarbatusHttpException(String message) {
        super(message);
    }

    public BarbatusHttpException(Exception exception) {
        super(exception);
    }

}
