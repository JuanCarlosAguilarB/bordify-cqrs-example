package com.bordify.userdetail.domain;


public class UserDetailPhoneNumber {

    private final String value;

    public UserDetailPhoneNumber(String userId) {
        this.value = userId;
    }

    public String value() {
        return value;
    }
}
