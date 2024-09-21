package com.bordify.userdetail.infrastructure.controllers;

import com.bordify.shared.domain.bus.command.CommandBus;
import com.bordify.userdetail.application.update.UpdateUserDetailCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Tag(name = "UserDetail", description = "UserDetail management operations")
@RestController
@AllArgsConstructor
public class UserDetailPatchController {

    private final CommandBus bus;

    @Operation(summary = "Modify information of the user", description = "Modify information of the user", tags = {"UserDetail"})
    @PatchMapping(value = "/v1/users/{id}")
    public ResponseEntity<?> getUser(@RequestBody Map<String, Object> userData, @PathVariable UUID id) {

        UpdateUserDetailCommand command = new UpdateUserDetailCommand(id, userData);
        bus.send(command);
        return ResponseEntity.ok(Map.of("message", "user updated"));

    }


}
