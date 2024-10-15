package com.bordify.board.application.create;

import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardName;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateBoardCommandHandler implements CommandHandler<CreateBoardCommand> {

    private final BoardCreator service;

    @Override
    public void handle(CreateBoardCommand command) {
        service.create(
                new BoardId(command.getId()),
                new BoardName(command.getName()),
                new UserUserId(command.getUserId())
        );
    }
}
