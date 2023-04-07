package com.project.gamelibrary.controller;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.service.BoardCommentService;
import com.project.gamelibrary.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    private final BoardService boardService;

    @ResponseBody
    @PostMapping("/boardcmt/{id}/add")
    public void addComment(@PathVariable("id") Long BoardId) {
        Optional<Board> board = boardService.findOne(BoardId);




    }

}
