package com.bordify.board.application.delete;

import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardRepository;
import com.bordify.shared.domain.EntityNotFound;
import org.springframework.stereotype.Service;

@Service
public class BoardDeleter {

    private final BoardRepository boardRepository;

    public BoardDeleter(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * Deletes a board by its ID.
     *
     * @param boardId The UUID of the board to delete.
     * @throws EntityNotFound if no board exists with the given ID.
     */
    public void delete(BoardId id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFound("Error deleting board: Board not found");
        }
        boardRepository.deleteById(id);
    }


}
