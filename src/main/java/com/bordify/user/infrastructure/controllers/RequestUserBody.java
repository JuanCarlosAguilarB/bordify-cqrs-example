package com.bordify.user.infrastructure.controllers;

import com.bordify.shared.domain.InvalidRequestArgumentException;
import com.bordify.utils.EmailValidator;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestUserBody {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

}
