package com.bordify.board.application.find;

import com.bordify.shared.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindBoardByIdQuery implements Query {
    UUID id;
}
