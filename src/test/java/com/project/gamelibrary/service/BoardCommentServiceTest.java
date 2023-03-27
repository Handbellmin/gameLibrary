package com.project.gamelibrary.service;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.domain.QBoard;
import com.project.gamelibrary.domain.QBoardComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;



@SpringBootTest
@Transactional
public class BoardCommentServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    JPAQueryFactory jpaQueryFactory;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardCommentService boardCommentService;
    
    @Test
    @Rollback(value = false)
    public void selectByBoardId() throws Exception {
        //given
        Board board = new Board.Builder("제목","내용","작성자","N").build();
    }
        
}