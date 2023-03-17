package com.project.gamelibrary.controller;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public String boardList(Model model){
        List<Board> boardList = boardService.findAll();

        model.addAttribute(boardList);
        return "";
    }
}
