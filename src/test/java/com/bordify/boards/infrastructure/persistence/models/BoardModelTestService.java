package com.bordify.boards.infrastructure.persistence.models;

import com.bordify.board.domain.Board;
import com.bordify.board.infrastructure.persistence.BoardEntity;
import com.bordify.userdetail.infrastructure.persistence.UserDetailEntity;

import java.util.UUID;

public class BoardModelTestService {

    public static BoardEntity createValidBoard(UserDetailEntity user) {

//        User user = UserModelTestService.createValidUser();
        UUID userId = user.getId();

        return BoardEntity.builder()
                .id(UUID.randomUUID())
                .name("Test BoardEntity")
                .user(user)
                .userId(userId)
                .build();
    }

    public static Board createValidBoardDomain(User user) {
        return Board.builder()
                .id(UUID.randomUUID())
                .name("Test Board")
                .user(user)
                .build();
    }

}
