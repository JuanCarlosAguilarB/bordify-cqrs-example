package com.bordify.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;
import java.util.UUID;


@Data
@Builder
@ToString
@AllArgsConstructor
public class User {

    private UUID        id;
    private String      username;
    private String      password;
    private String      email;
    private String      firstName;
    private String      lastName;
    private Boolean     isVerified;
    private String      phoneNumber;
    private LocalTime   created;
    private LocalTime   lastLogin;

}
