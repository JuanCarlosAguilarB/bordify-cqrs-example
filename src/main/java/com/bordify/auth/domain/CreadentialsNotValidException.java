package com.bordify.auth.domain;

public class CreadentialsNotValidException extends RuntimeException {

    public CreadentialsNotValidException(String message) {
        super(message);
    }
}
