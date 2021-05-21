package com.epam.web.exception;

public class DaoException extends Exception {

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String s) {
        super(s);
    }

}
