package com.bordify.board.infrastructure.mapper;

import com.bordify.board.domain.Board;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.infrastructure.mapper.UserDetailMapper;

public class BoardMapper {

    public Board toDomain(BoardEntity boardEntity) {

        UserDetail user = UserDetailMapper.toDomain(boardEntity.getUser());

        return Board.builder()
                .id(boardEntity.getId())
                .name(boardEntity.getName())
                .user(user)
                .build();
    }

    public BoardEntity toEntity(Board board) {
        return BoardEntity.builder()
                .id(board.getId())
                .name(board.getName())
                .userId(board.getUser().id().value())
                .build();
    }

}
