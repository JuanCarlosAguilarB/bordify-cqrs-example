package com.bordify.board.infrastructure.controllers;

import com.bordify.board.application.delete.DeleteBoardCommand;
import com.bordify.shared.domain.bus.command.CommandBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Board", description = "Board management operations")
@AllArgsConstructor
public class BoardDeleteController {

    private final CommandBus bus;

//    public BoardDeleteController(BoardDeleter boardDeleter) {
//        this.boardDeleter = boardDeleter;
//    }

    /**
     * Deletes a board by its id.
     *
     * @param boardId The id of the board to be deleted.
     * @return A ResponseEntity with no content.
     */
    @Operation(summary = "Delete a board", description = "Deletes a board by its id", tags = {"Board"})
    @DeleteMapping("/v1/boards/{boardId}/")
    public ResponseEntity<?> deleteBoard(@PathVariable UUID boardId) {
        DeleteBoardCommand command = new DeleteBoardCommand(boardId);
        bus.send(command);
        return ResponseEntity.ok().build();
    }

}
