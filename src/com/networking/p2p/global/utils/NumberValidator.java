package com.networking.p2p.global.utils;

import com.networking.p2p.global.exception.InvalidPortNumberException;

import static com.networking.p2p.global.exception.ExceptionType.*;

public class NumberValidator {
    private static final String PORT_PATTERN = "^(?:[1-9]\\d{0,4}|0)$";

    public static void validatePortNumber(String portFormat) throws InvalidPortNumberException {
        if (portFormat.isBlank()) {
            throw new InvalidPortNumberException(EMPTY_PORT_NUMBER.getMessage());
        }

        if (!portFormat.matches(PORT_PATTERN)) {
            throw new InvalidPortNumberException(INVALID_PORT_NUMBER.getMessage());
        }
    }
}
