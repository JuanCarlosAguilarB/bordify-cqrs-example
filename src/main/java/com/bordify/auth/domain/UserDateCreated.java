package com.bordify.auth.domain;

import java.time.LocalDateTime;

public class UserDateCreated {

    private LocalDateTime value;

    public UserDateCreated(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }

}
