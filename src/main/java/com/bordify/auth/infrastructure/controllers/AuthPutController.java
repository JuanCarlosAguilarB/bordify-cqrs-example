package com.bordify.auth.infrastructure.controllers;

import com.bordify.auth.application.create.CreateUserCommand;
import com.bordify.shared.domain.bus.command.CommandBus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "Auth", description = "Authentication operations")
@AllArgsConstructor
public class AuthPutController {

    private final CommandBus bus;

    @PutMapping("/v1/signup/{userId}/")
    public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody UserRequest userRequest, @PathVariable UUID userId) {

        CreateUserCommand user = CreateUserCommand.builder()
                .userId(userId)
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        bus.send(user);
        return ResponseEntity.ok(Map.of("message", "UserDetail created successfully"));
    }

}


@Builder
@Data
class UserRequest {

    private String email;
    private String username;
    private String password;
}
