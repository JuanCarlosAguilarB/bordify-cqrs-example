package com.bordify.board.domain;

import com.bordify.shared.domain.UserUserId;
import com.bordify.userdetail.domain.UserDetail;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Board {

    private BoardId id;
    private BoardName name;
    private UserUserId userId;

    public BoardId id(){return this.id;}
    public BoardName name(){return this.name;}
    public UserUserId userId(){return this.userId;}
}
