package com.bordify.board.infrastructure.controllers;

import com.bordify.board.application.find.BoardFinder;
import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardResponse;
import com.bordify.configuration.infrastructure.ApiExceptionResponse;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.query.QueryBus;
import com.bordify.topic.application.find.TopicFinder;
import com.bordify.topic.domain.TopicListDTO;
import com.bordify.userdetail.application.find.UserDetailFinder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(name = "Board", description = "Board management operations")
public class BoardGetController {

    private QueryBus bus;

    private final BoardFinder boardFinder;
    private final UserDetailFinder userDetailFinder;
    private final TopicFinder topicFinder;


    /**
     * List all boards.
     *
     * @param pageable The pagination information.
     * @param auth     The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with the list of boards.
     */
    @GetMapping("/v1/boards/")
    @Operation(summary = "List boards", description = "List all boards", tags = {"Board"})
    public ResponseEntity<?> listBoards(Pageable pageable, UserUserId userId) {

        PaginationRequest paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());

        return ResponseEntity.ok(boardFinder.findAllBoards(paginationRequest, userId));

    }


    /**
     * Get all topics of a board.
     *
     * @param boardId  The id of the board to retrieve topics for.
     * @param pageable The pagination information.
     * @param auth     The authentication object containing information about the authenticated user.
     * @return A ResponseEntity with the topics of the board.
     */
    @Operation(summary = "Get all topics of a board",
            description = "Lists all topics of a board for a given board",
            tags = {"Board"})
    @GetMapping("/v1/boards/{boardId}/topics/")
    public ResponseEntity<?> getTopicsOfBoard(
            @PathVariable UUID boardId,
            Pageable pageable,
            UserUserId userId) {

        // verify that owner of the board is the one requesting the topics
        BoardResponse board = boardFinder.findBoardById(new BoardId(boardId));
//        boolean isUserOwnerOfBoard = user.id().value() == board.userId().value();
        boolean isUserOwnerOfBoard = userId.value() == board.getUserId();

        if (!isUserOwnerOfBoard) {

            ApiExceptionResponse response = ApiExceptionResponse.builder()
                    .status(HttpStatus.FORBIDDEN.value())
                    .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                    .message("UserDetail is not the owner of the board")
                    .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        PaginationRequest paginationRequest = new PaginationRequest(pageable.getPageNumber(), pageable.getPageSize());

        List<TopicListDTO> topics = topicFinder.getTopicsOfBoard(boardId, paginationRequest);
        Page<TopicListDTO> topicPaginated = new PageImpl<>(topics, pageable, topics.size());
        return ResponseEntity.ok(topicPaginated);
    }

}
