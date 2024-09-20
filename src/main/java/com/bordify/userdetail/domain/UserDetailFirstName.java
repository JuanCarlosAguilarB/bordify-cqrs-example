package com.bordify.userdetail.domain;

public class UserDetailFirstName {

    private final String value;

    public UserDetailFirstName(String userId) {
        this.value = userId;
    }

    public String value() {
        return value;
    }
}
