package com.bordify.shared.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class UserUserName {

    private final String value;

    public UserUserName(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
