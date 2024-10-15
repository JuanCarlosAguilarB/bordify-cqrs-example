package com.bordify.board.application.create;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateBoardCommand implements Command {
    UUID id;
    String name;
    UUID userId;
}
