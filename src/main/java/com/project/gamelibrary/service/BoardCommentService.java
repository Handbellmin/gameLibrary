package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardCommentForm;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.repository.BoardCommentRepository;
import com.project.gamelibrary.repository.impl.BoardCommentCustomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentCustomRepositoryImpl boardCommentCustomRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardService boardService;

    public void saveBoardComment(BoardCommentForm boardCommentForm) {
        Board board = null;
        try {
            board = boardService.findOne(boardCommentForm.getBoardId()).isPresent() ? boardService.findOne(boardCommentForm.getBoardId()).get() : null;
        } catch (Exception e) {
            log.error("없는 게시물입니다.");
        }
        if (board != null) {
            BoardComment boardComment = new BoardComment.Builder(
                    board,
                    boardCommentForm.getCmtContent(),
                    boardCommentForm.getCreateId(),
                    LocalDateTime.now()
            ).build();
            boardCommentRepository.save(boardComment);
        }
    }

    public List<BoardComment> findAllByBoardId(Long boardId) {
        return boardCommentCustomRepository.findAllByBoardId(boardId);
    }

}
