package com.bordify.auth.domain;

public class UserIsVerified {
    private boolean value;

    public UserIsVerified(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }
}
