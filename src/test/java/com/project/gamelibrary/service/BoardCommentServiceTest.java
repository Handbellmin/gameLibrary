package com.project.gamelibrary.service;

import com.mysema.commons.lang.Assert;
import com.project.gamelibrary.Form.BoardForm;
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
    public void selectByBoardId() throws Exception {
        //given
        BoardForm boardForm = new BoardForm();
        boardForm.setTtl("test");
        boardForm.setContent("content");
        boardForm.setPopupYn("N");
        boardService.saveBoard(boardForm);

        Board asBoard = boardService.findOne(1L);
        Assertions.assertEquals(asBoard.getContent(),"content");

    }
        
}