package com.bordify.board.infrastructure.mapper;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardName;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.infrastructure.mapper.UserDetailMapper;

public class BoardMapper {

    public Board toDomain(BoardEntity boardEntity) {

        UserDetail user = UserDetailMapper.toDomain(boardEntity.getUser());
        return new Board(
                new BoardId(boardEntity.getId()),
                new BoardName(boardEntity.getName()),
                user
        );
    }

    public BoardEntity toEntity(Board board) {
        return BoardEntity.builder()
                .id(board.id().value())
                .name(board.name().value())
                .userId(board.user().id().value())
                .build();
    }

}
