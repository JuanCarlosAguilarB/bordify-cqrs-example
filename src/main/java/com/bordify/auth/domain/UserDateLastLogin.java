package com.bordify.auth.domain;

import java.time.LocalDateTime;

public class UserDateLastLogin {

    private LocalDateTime value;

    public UserDateLastLogin(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }
}
