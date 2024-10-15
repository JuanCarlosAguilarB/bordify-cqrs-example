package com.bordify.board.infrastructure.mapper;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardName;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.shared.domain.UserUserId;

public class BoardMapper {

    public Board toDomain(BoardEntity boardEntity) {

        return new Board(
                new BoardId(boardEntity.getId()),
                new BoardName(boardEntity.getName()),
                new UserUserId(boardEntity.getUserId())
        );
    }

    public BoardEntity toEntity(Board board) {
        return BoardEntity.builder()
                .id(board.id().value())
                .name(board.name().value())
                .userId(board.userId().value())
                .build();
    }

}
