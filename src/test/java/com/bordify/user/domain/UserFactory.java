package com.bordify.user.domain;

import java.time.LocalTime;
import java.util.UUID;

import static com.bordify.shared.domain.FactoryValues.*;

public class UserFactory {


    public static User createRandomUser() {
        LocalTime currentTime = LocalTime.now();

        return User.builder()
                .id(UUID.randomUUID())
                .username(generateRandomAlphanumeric(10))
                .password(generateRandomAlphanumeric(10))
                .email(generateRandomEmail())
                .firstName(generateRandomString(5))
                .lastName(generateRandomString(7))
                .isVerified(false)
                .phoneNumber(generateRandomPhoneNumber())
                .created(currentTime)
                .lastLogin(null)
                .build();
    }

}