package com.bordify.userdetail.domain;

public class UserDetailLastName {

    private final String value;

    public UserDetailLastName(String userId) {
        this.value = userId;
    }

    public String value() {
        return value;
    }
}
