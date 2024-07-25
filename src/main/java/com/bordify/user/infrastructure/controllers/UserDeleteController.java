package com.bordify.user.infrastructure.controllers;

import com.bordify.user.application.delete.UserDeleter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "User", description = "User management operations")
@RestController
@AllArgsConstructor
public class UserDeleteController {

    private final UserDeleter userServices;

    /**
     * Deletes a user by your id.
     *
     * @param id The id of the user to be deleted.
     * @return A ResponseEntity with no content.
     */
    @Operation(summary = "Delete a user", description = "Deletes a user by your id", tags = {"User"})
    @DeleteMapping("/v1/users/{id}/")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        userServices.delete(id);
        return ResponseEntity.ok().build();
    }

}
