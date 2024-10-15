package com.bordify.board.application.delete;

import com.bordify.board.domain.BoardId;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteBoardCommandHandler implements CommandHandler<DeleteBoardCommand> {

    private final BoardDeleter service;

    @Override
    public void handle(DeleteBoardCommand command) {
        service.delete(
                new BoardId(command.getId())
        );
    }
}
