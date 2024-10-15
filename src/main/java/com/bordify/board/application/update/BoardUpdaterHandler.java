package com.bordify.board.application.update;

import com.bordify.board.domain.BoardId;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardUpdaterHandler implements CommandHandler<UpdateBoardCommand> {

    private final BoardUpdater service;

    @Override
    public void handle(UpdateBoardCommand command) {
        service.update(
                new BoardId(command.getId()),
                command.getData()
        );
    }
}
