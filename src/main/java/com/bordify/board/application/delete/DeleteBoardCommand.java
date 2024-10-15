package com.bordify.board.application.delete;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteBoardCommand implements Command {
    UUID id;
}
