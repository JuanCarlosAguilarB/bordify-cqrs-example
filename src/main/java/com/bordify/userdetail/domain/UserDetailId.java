package com.bordify.userdetail.domain;

import java.util.UUID;

public class UserDetailId {

    private final UUID value;

    public UserDetailId(UUID userId) {
        this.value = userId;
    }

    public UserDetailId(String userId) {
        this.value = UUID.fromString(userId);
    }

    public UUID value() {
        return value;
    }

}
