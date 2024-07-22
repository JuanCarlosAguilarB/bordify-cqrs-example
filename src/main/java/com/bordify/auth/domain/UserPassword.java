package com.bordify.auth.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class UserPassword {


    private final String value;

    public UserPassword(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
