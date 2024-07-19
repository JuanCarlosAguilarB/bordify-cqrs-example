package com.bordify.user.infrastructure.controllers;

import com.bordify.user.application.create.UserCreator;
import com.bordify.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;


@Tag(name = "User", description = "User management operations")
@RestController
public class UserPutController {

//    private final PasswordEncoder passwordEncoder;

    private final UserCreator userCreatorServices;

    public UserPutController(
//            PasswordEncoder passwordEncoder,
            UserCreator userCreatorServices
    ) {
//        this.passwordEncoder = passwordEncoder;
        this.userCreatorServices = userCreatorServices;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user", tags = { "User" })
    @PutMapping(value = "/v1/users/{id}/")
    public ResponseEntity<?> createUser(
            @RequestBody RequestUserBody requestBody,
            @PathVariable UUID id
    ) {

        User user = User.builder()
                .id(id)
                .username(requestBody.getUsername())
                .email(requestBody.getEmail())
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .phoneNumber(requestBody.getPhoneNumber())
                .password(requestBody.getPassword())
                .build();

        userCreatorServices.createUser(user);

        Map<String,String> response = Map.of("message","User created");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
