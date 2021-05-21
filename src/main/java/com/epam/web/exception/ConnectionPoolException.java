package com.epam.web.exception;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }

    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }

}
