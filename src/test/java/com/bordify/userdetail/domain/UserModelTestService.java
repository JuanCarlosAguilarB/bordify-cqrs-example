package com.bordify.userdetail.domain;


import com.bordify.userdetail.infrastructure.persistence.UserDetailEntity;

import java.time.LocalTime;
import java.util.UUID;

public class UserModelTestService {

    public static UserDetailEntity createValidUserEntity() {

        LocalTime currentTime = LocalTime.now();

        UserDetailEntity user = UserDetailEntity.builder()
                .id(UUID.randomUUID())
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .firstName("John")
                .lastName("Doe")
                .isVerified(false)
                .phoneNumber("+1234567890")
                .created(currentTime)
                .lastLogin(currentTime)
                .build();

        return user;
    }


    public static User createValidUserDomain() {

        LocalTime currentTime = LocalTime.now();

        User user = User.builder()
                .id(UUID.randomUUID())
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .firstName("John")
                .lastName("Doe")
                .isVerified(false)
                .phoneNumber("+1234567890")
                .created(currentTime)
                .lastLogin(currentTime)
                .build();

        return user;
    }

}
