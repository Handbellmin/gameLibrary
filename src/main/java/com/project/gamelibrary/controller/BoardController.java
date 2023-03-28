package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.service.BoardCategoryService;
import com.project.gamelibrary.service.BoardCommentService;
import com.project.gamelibrary.service.BoardService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;
    private final BoardCategoryService boardCategoryService;

    @GetMapping("/boards")
    public String boardList(Model model){
        List<Board> boardList = boardService.findAll();

        model.addAttribute("boards",boardList);
        return "/boards/boardList";
    }

    @GetMapping("/boards/new")
    public String createForm(Model model){
        model.addAttribute("boardCategories", boardCategoryService.findAll());
        model.addAttribute("form", new BoardForm());
        return "/boards/createBoardForm";
    }

    @PostMapping("/boards/new")
    public String create(@AuthenticationPrincipal PrincipalDetails userDetails,
            BoardForm boardForm){
        boardForm.setCreateId(userDetails.getUser().getUsername());
        boardService.saveBoard(boardForm);
        return "redirect:/boards";
    }

    @GetMapping("/boards/{id}/edit")
    public String editForm(@PathVariable("id") Long BoardId, Model model){
        Board board = boardService.findOne(BoardId);
        model.addAttribute("boardCategories", boardCategoryService.findAll());
        model.addAttribute("form", board);

        return "/boards/createBoardForm";
    }

    @GetMapping("/boards/{id}/detail")
    public String detail(@PathVariable ("id") Long BoardId, Model model) {
        Board board = boardService.findOne(BoardId);
        List<BoardComment> boardComments = boardCommentService.findAllByBoardId(BoardId);

        model.addAttribute("board", board);
        model.addAttribute("boardComments", boardComments);

        return "/boards/details";
    }
}
