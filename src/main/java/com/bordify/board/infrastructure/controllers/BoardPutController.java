package com.bordify.board.infrastructure.controllers;


import com.bordify.board.application.create.CreateBoardCommand;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.command.CommandBus;
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


@RestController()
@Tag(name = "Board", description = "Board management operations")
public class BoardPutController {

    private final CommandBus bus;

    public BoardPutController(CommandBus bus) {
        this.bus = bus;
    }


    /**
     * Creates a new board for the authenticated user.
     *
     * @param boardRequest The request containing the name of the board to be created.
//     * @param auth         The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with message the created board.
     */

    @Operation(summary = "Create a new board", description = "Creates a new board for the authenticated user", tags = {"Board"})
    @PutMapping(value = "/v1/boards/{id}/")
    public ResponseEntity<?> create(
            @RequestBody BoardRequest boardRequest,
            @PathVariable UUID id,
            UserUserId userId // injected by spring boot

    ) {

        CreateBoardCommand command = new CreateBoardCommand(
                id,
                boardRequest.getName(),
                userId.value()
        );

        bus.send(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Board created"));
    }

}
