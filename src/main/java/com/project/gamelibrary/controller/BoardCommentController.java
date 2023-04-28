package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.BoardCommentForm;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.service.BoardCommentService;
import com.project.gamelibrary.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    private final BoardService boardService;

    @PostMapping("/boardCmt/{id}/add")
    public String addComment(@AuthenticationPrincipal PrincipalDetails userDetails,
                            BoardCommentForm boardCmtForm,
                           @PathVariable("id") Long boardId
    ) throws Exception {
        if (boardCmtForm != null) {
            //댓글 저장
            boardCmtForm.setBoardId(boardId);
            boardCmtForm.setCreateId(userDetails.getUser().getUsername());
            boardCommentService.saveBoardComment(boardCmtForm);
        }
        return "redirect:/boards/"+boardId+"/detail";
    }
}
