package com.bordify.shared.domain;

public class CreadentialsNotValidException extends RuntimeException {

    public CreadentialsNotValidException(String message) {
        super(message);
    }
}
