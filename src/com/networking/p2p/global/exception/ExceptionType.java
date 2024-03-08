package com.networking.p2p.global.exception;

public enum ExceptionType {
    INVALID_PORT_NUMBER("유효하지 않은 포트 번호 입니다. (유효한 포트 번호 : 0 ~  65535)"),
    EMPTY_PORT_NUMBER("포트 번호가 빈 값입니다.");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
