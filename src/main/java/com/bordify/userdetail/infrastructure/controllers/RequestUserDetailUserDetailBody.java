package com.bordify.userdetail.infrastructure.controllers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestUserDetailUserDetailBody {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

}
