package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void saveBoard(Board board) {boardRepository.save(board);}

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
