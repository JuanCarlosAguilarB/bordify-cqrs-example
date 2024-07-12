package com.bordify.user.infrastructure.controllers;

import com.bordify.user.application.update.UserUpdater;
import com.bordify.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Tag(name = "User", description = "User management operations")
@RestController
@AllArgsConstructor
public class UserPatchController {

    private final UserUpdater userServices;

    @Operation(summary = "Modify information of the user", description = "Modify information of the user", tags = { "User" })
    @PatchMapping(value = "/v1/users/{id}")
    public ResponseEntity<?> getUser(@RequestBody Map<String, Object> userData, @RequestParam UUID id) {

        userServices.update(id, userData);

        Map<String, String> response = Map.of("message", "topic updated");
        return ResponseEntity.ok(response);

    }


}