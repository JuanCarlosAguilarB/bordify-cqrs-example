package com.bordify.auth.infrastructure.controllers;

import com.bordify.auth.application.create.UserAuthInformationCreator;
import com.bordify.auth.domain.UserAuthInformation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "Auth", description = "Authentication operations")
@AllArgsConstructor
public class AuthPutController {

    private final UserAuthInformationCreator userServices;

    @PutMapping("/v1/signup/{userId}/")
    public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody UserAuthInformationRequest userRequest, @PathVariable UUID userId) {

        UserAuthInformation userAuthInformation = UserAuthInformation.builder()
                .userId(userId)
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .isVerified(false)
                .created(LocalTime.now())
                .lastLogin(null)
                .build();

        userServices.createUser(userAuthInformation);

        return ResponseEntity.ok(Map.of("message", "User created successfully"));
    }

}


@Builder
@Data
class UserAuthInformationRequest {

    private String email;
    private String username;
    private String password;
}
