package com.bordify.board.application.find;

import com.bordify.board.domain.*;
import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.domain.UserUserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardFinder {

    private final BoardRepository boardRepository;

    /**
     * Lists all boards for a given user.
     *
     * @param pageable Pagination information.
     * @param userId   The UUID of the user whose boards to list.
     * @return A page of {@link BoardListDTO} objects.
     */
    public PageResult<BoardListDTO> findAllBoards(PaginationRequest pageable, UserUserId userId) {
        return boardRepository.findByUserId(pageable, userId);
    }

    public BoardResponse findBoardById(BoardId id) {
        Board board =  boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        return  BoardResponse.builder()
                .id(board.id().value())
                .name(board.name().value())
                .userId(board.userId().value())
                .build();
    }

}
