package com.networking.p2p.global.exception;

import javax.management.InvalidApplicationException;

public class InvalidPortNumberException extends InvalidApplicationException {
    public InvalidPortNumberException(String message) {
        super(message);
    }
}
