package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardForm;
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
    @Transactional
    public void saveBoard(BoardForm boardForm) {
        // Insert
        if (boardForm.getId() != null ) {
            Board asBoard = boardRepository.findOne(boardForm.getId());
            asBoard.update(boardForm.getTtl(), boardForm.getContent(), boardForm.getPopupYn());
            return;
        }
        boardRepository.save(boardForm.toEntity());
        return;
    }


    public List<Board> findAll() {
        return boardRepository.findAll();
    }
    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
