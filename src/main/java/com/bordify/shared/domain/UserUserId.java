package com.bordify.shared.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class UserUserId {

    private final UUID userId;

    public UserUserId(UUID userId) {
        this.userId = userId;
    }

    public UserUserId(String userId) {
        this.userId = UUID.fromString(userId);
    }

    public UUID value() {
        return userId;
    }
}