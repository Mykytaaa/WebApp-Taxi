package com.zakharenko.finaltask.taxi.model.dao.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException() {
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Exception e) {
        super(e);
    }

    public DatabaseException(String message, Exception e) {
        super(message, e);
    }
}
