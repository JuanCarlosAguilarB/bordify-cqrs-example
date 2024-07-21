package com.bordify.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UserAuthInformation {

    private UUID        userId;
    private String      email;
    private String      username;
    private String      password;
    private Boolean     isVerified;
    private LocalTime   created;
    private LocalTime   lastLogin;
}
