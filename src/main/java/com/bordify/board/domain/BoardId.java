package com.bordify.board.domain;

import java.util.UUID;

public class BoardId {

    private UUID value;

    public BoardId(UUID value){this.value=value;}
    public BoardId(String value){this.value=UUID.fromString(value);}

    public UUID value() {
        return value;
    }
}
