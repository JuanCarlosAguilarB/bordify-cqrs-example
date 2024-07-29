package com.bordify.userdetail.infrastructure.controllers;

import com.bordify.userdetail.application.create.UserCreator;
import com.bordify.userdetail.domain.UserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;


@Tag(name = "UserDetail", description = "UserDetail management operations")
@RestController
@AllArgsConstructor
public class UserPutController {

    private final UserCreator userCreatorServices;

    @Operation(summary = "Create a new user", description = "Creates a new user", tags = {"UserDetail"})
    @PutMapping(value = "/v1/users/{id}/")
    public ResponseEntity<?> createUser(
            @RequestBody RequestUserBody requestBody,
            @PathVariable UUID id
    ) {

        UserDetail user = UserDetail.builder()
                .id(id)
                .username(requestBody.getUsername())
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .phoneNumber(requestBody.getPhoneNumber())
                .build();

        userCreatorServices.createUser(user);

        Map<String, String> response = Map.of("message", "UserDetail created");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
