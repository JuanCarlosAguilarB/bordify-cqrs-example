package com.bordify.board.domain;

import com.bordify.shared.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse implements Response {

    private UUID id;
    private String name;
    private UUID userId;

}
