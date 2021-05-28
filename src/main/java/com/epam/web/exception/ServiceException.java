package com.epam.web.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Throwable cause) {
        super(cause);
    }

}
