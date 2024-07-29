package com.bordify.userdetails.infrastructure.controllers;

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
public class UserResponse {


    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalTime created;
    private LocalTime lastLogin;

}
