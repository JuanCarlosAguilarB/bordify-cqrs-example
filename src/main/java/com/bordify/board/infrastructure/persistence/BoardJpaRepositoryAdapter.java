package com.bordify.board.infrastructure.persistence;

import com.bordify.board.domain.Board;
import com.bordify.board.domain.BoardId;
import com.bordify.board.domain.BoardListDTO;
import com.bordify.board.domain.BoardRepository;
import com.bordify.board.infrastructure.mapper.BoardMapper;
import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.domain.UserUserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BoardJpaRepositoryAdapter implements BoardRepository {

    private final BoardJpaRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardJpaRepositoryAdapter(BoardJpaRepository boardJpaRepository) {
        this.boardRepository = boardJpaRepository;
        this.boardMapper = new BoardMapper();
    }

//    @Override
//    public void deleteById(UUID boardId) {
//        boardRepository.deleteById(boardId);
//    }
//
//    @Override
//    public boolean existsById(UUID boardId) {
//        return boardRepository.existsById(boardId);
//    }

    @Override
    public void deleteById(BoardId id) {

    }

    @Override
    public boolean existsById(BoardId id) {
        return false;
    }

    @Override
    public PageResult<BoardListDTO> findBy(PaginationRequest pageable) {

        Pageable pageableResult = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); // page 0, size 20
        Page<BoardListDTO> page = boardRepository.findBy(pageableResult);

        PageResult<BoardListDTO> pageResult = new PageResult<BoardListDTO>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements());

        return pageResult;
    }

    @Override
    public PageResult<BoardListDTO> findByUserId(PaginationRequest pageable, UserUserId userId) {
        return null;
    }

    @Override
    public Board findDtoById(BoardId id) {
        return null;
    }

//    @Override
//    public PageResult<BoardListDTO> findByUserId(PaginationRequest pageable, UUID userId) {
//
//        Pageable pageableResult = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); // page 0, size 20
//        Page<BoardListDTO> page = boardRepository.findByUserId(pageableResult, userId);
//
//        PageResult<BoardListDTO> pageResult = new PageResult<BoardListDTO>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements());
//
//        return pageResult;
//    }

//    @Override
//    public Board findDtoById(UUID boardId) {
//
//        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(null);
//
//        if (boardEntity != null) {
//            return boardMapper.toDomain(boardEntity);
//        }
//
//        return null;
//    }

    @Override
    public void save(Board board) {
        BoardEntity boardEntity = boardMapper.toEntity(board);
        boardRepository.save(boardEntity);
    }

    @Override
    public Optional<Board> findById(BoardId id) {
        return Optional.empty();
    }

//    @Override
//    public Optional<Board> findById(UUID boardId) {
//        Optional<BoardEntity> boardEntity = boardRepository.findBoardEntityById(boardId);
////        if (boardEntity.isPresent()) {
////            return Optional.of(boardMapper.toDomain(boardEntity.get()));
////        }
////        return Optional.empty();
//
//        return boardEntity.map(boardMapper::toDomain);
//    }
}
