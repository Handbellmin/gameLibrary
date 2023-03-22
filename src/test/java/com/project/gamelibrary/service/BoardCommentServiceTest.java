package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.domain.QBoard;
import com.project.gamelibrary.domain.QBoardComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BoardCommentServiceTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardCommentService boardCommentService;
    
    @Test
    public void selectByBoardId() throws Exception {
        //given
        Board board = new Board.Builder("제목","내용","작성자","N", LocalDateTime.now()).build();
        BoardComment boardComment = new BoardComment.Builder("댓글내용")
                .setCreateDate(LocalDateTime.now())
                .setCreateId("댓글작성자")
                .setBoard(board)
                .build();
        //when
        boardService.saveBoard(board);
        boardCommentService.saveBoardComment(boardComment);
        //then
        QBoardComment qBoardComment = QBoardComment.boardComment;
        QBoard qBoard = QBoard.board;
        jpaQueryFactory.select(qBoardComment)
                .from(qBoardComment)
                .join(qBoardComment.board,qBoard)
                .fetchJoin()
                .fetch();

        List<BoardComment> byBoardId = boardCommentService.findByBoardId(board.getId());
        for (BoardComment bm : byBoardId){
            System.out.println(bm.getCmtContent());
        }
    }
        
}