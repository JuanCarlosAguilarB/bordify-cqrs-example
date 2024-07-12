package com.bordify.user.infrastructure.controllers;


import com.bordify.user.application.find.UserFinder;
import com.bordify.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User management operations")
@RestController
@AllArgsConstructor
public class UserGetController {

    private final UserFinder userServices;

    @Operation(summary = "Get information of the user", description = "Get a user", tags = { "User" })
    @GetMapping(value = "/v1/users/me/")
    public User getUser(Authentication authentication) {

        String userame = authentication.getName();

        User user = userServices.findUserByUsername(userame);

        return user;

    }

}
