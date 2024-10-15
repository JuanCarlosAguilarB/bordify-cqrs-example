package com.bordify.board.application.update;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateBoardCommand implements Command {
    UUID id;
    Map<String, Object> data;
}
