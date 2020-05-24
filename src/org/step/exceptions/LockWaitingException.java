package org.step.exceptions;

public class LockWaitingException extends RuntimeException {

    public LockWaitingException(String message) {
        super(message);
    }
}
