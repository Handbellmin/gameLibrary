package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.repository.BoardCommentRepository;
import com.project.gamelibrary.repository.impl.BoardCommentCustomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentCustomRepositoryImpl boardCommentCustomRepository;
    private final BoardCommentRepository boardCommentRepository;

    public void saveBoardComment(BoardComment boardComment) {boardCommentRepository.save(boardComment);}

    public List<BoardComment> findAllByBoardId(Long boardId) {
        return boardCommentCustomRepository.findAllByBoardId(boardId);
    }

}
