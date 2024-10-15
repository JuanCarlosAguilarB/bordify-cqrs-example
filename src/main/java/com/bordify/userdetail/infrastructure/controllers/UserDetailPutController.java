package com.bordify.userdetail.infrastructure.controllers;

import com.bordify.shared.domain.bus.command.CommandBus;
import com.bordify.userdetail.application.create.CreateUserDetailCommand;
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
public class UserDetailPutController {

    private final CommandBus bus;

    @Operation(summary = "Create a new user", description = "Creates a new user", tags = {"UserDetail"})
    @PutMapping(value = "/v1/users/{id}/")
    public ResponseEntity<?> createUser(
            @RequestBody RequestUserDetailUserDetailBody requestBody,
            @PathVariable UUID id
    ) {

        CreateUserDetailCommand command = CreateUserDetailCommand.builder()
                .id(id)
                .userName(requestBody.getUsername())
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .phoneNumber(requestBody.getPhoneNumber())
                .build();

        bus.send(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "UserDetail created"));
    }

}
