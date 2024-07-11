package com.bordify.board.domain;

import com.bordify.user.domain.User;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Board {

    private UUID        id;
    private String      name;
    private User user;

}
