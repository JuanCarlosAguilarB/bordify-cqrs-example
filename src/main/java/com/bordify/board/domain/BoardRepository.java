package com.bordify.board.domain;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.domain.UserUserId;

import java.util.Optional;

/**
 * Repository interface for accessing and managing board entities in the database.
 */
public interface BoardRepository {

    /**
     * Deletes a board by its ID.
     *
     * @param id The ID of the board to delete.
     */
    public void deleteById(BoardId id);

    /**
     * Checks if a board exists by its ID.
     *
     * @param id The ID of the board to check.
     * @return True if the board exists, false otherwise.
     */
    public boolean existsById(BoardId id);

    /**
     * Retrieves a page of board DTOs.
     *
     * @param pageable The pagination information.
     * @return A PageResult of board DTOs.
     */
    public PageResult<BoardListDTO> findBy(PaginationRequest pageable);

    /**
     * Retrieves a page of board DTOs filtered by user ID.
     *
     * @param pageable The pagination information.
     * @param userId   The ID of the user.
     * @return A PageResult of board DTOs filtered by user ID.
     */
    public PageResult<BoardListDTO> findByUserId(PaginationRequest pageable, UserUserId userId);

    /**
     * Finds a board DTO by its ID.
     *
     * @param id The ID of the board.
     * @return The board DTO.
     */
    public Board findDtoById(BoardId id);


    /**
     * Saves a board entity to the database.
     *
     * @param board The board entity to save.
     */
    public void save(Board board);

    /**
     * Retrieves a board entity by its ID.
     *
     * @param id The ID of the board.
     * @return The Optional<Board>.
     */
    public Optional<Board> findById(BoardId id);
}
