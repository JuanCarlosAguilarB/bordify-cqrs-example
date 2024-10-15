package com.bordify.board.application.find;

import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardResponse;
import com.bordify.shared.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindBoardByIdQueryHandler implements QueryHandler<FindBoardByIdQuery, BoardResponse> {

    private final BoardFinder service;

    @Override
    public BoardResponse handle(FindBoardByIdQuery query) {
        service.findBoardById(new BoardId(query.getId()));
        return null;
    }
}
