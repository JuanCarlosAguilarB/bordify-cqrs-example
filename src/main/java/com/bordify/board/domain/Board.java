package com.bordify.board.domain;

import com.bordify.userdetail.domain.UserDetail;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Board {

    private BoardId id;
    private BoardName name;
    private UserDetail user;

    public BoardId id(){return this.id;}
    public BoardName name(){return this.name;}
    public UserDetail user(){return this.user;}
}
