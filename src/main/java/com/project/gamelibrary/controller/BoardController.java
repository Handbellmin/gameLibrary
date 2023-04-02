package com.project.gamelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.service.BoardCategoryService;
import com.project.gamelibrary.service.BoardCommentService;
import com.project.gamelibrary.service.BoardService;
import com.project.gamelibrary.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;
    private final BoardCategoryService boardCategoryService;
    private final FileService fileService;

    @GetMapping("/boards")
    public String boardList(@RequestParam(defaultValue = "0") int pageNumber, Model model){
        Page<Board> boards = boardService.findAllPage(pageNumber, 10);
        model.addAttribute("boardList",boards);

        return "/boards/boardList";
    }

    @GetMapping("/boards/new")
    public String createForm(Model model){
        model.addAttribute("boardCategories", boardCategoryService.findAll());
        model.addAttribute("boardForm", new BoardForm());
        return "/boards/createBoardForm";
    }

    @PostMapping("/boards/new")
    public String create(@AuthenticationPrincipal PrincipalDetails userDetails,
                         @RequestParam(name = "boardForm") String boardForm,
                         @RequestParam(name = "files", required = false) List<MultipartFile> file,
                         @RequestParam(name = "removeFile",required = false) String removeFile
    ) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BoardForm boardForm1 = mapper.readValue(boardForm,BoardForm.class);
        boardForm1.setCreateId(userDetails.getUser().getUsername());
        List<MultipartFile> multipartFiles = file.stream().filter(e -> e.getSize() > 0).toList();
        boardService.saveBoard(boardForm1, multipartFiles,removeFile);
        return "redirect:/boards";
    }

    @GetMapping("/boards/{id}/edit")
    public String editForm(@PathVariable("id") Long BoardId, Model model){
        Optional<Board> board = boardService.findOne(BoardId);
        model.addAttribute("boardCategories", boardCategoryService.findAll());
        model.addAttribute("boardForm", board.get());
        model.addAttribute("files", fileService.findByboardId(BoardId));

        return "/boards/createBoardForm";
    }

    @GetMapping("/boards/{id}/detail")
    public String detail(@PathVariable ("id") Long BoardId,
                         @RequestParam(defaultValue = "0") int pageNumber,
                         Model model) {
        Optional<Board> board = boardService.findOne(BoardId);
        List<BoardComment> boardComments = boardCommentService.findAllByBoardId(BoardId);

        model.addAttribute("board", board.get());
        model.addAttribute("boardComments", boardComments);
        model.addAttribute("files", fileService.findByboardId(BoardId));
        model.addAttribute("pageNumber",pageNumber);
        return "/boards/boarddetails";
    }
    @PostMapping(value="smarteditorMultiImageUpload")
    public void smarteditorImageUpload(HttpServletRequest request, HttpServletResponse response) {
        String sFileInfo = "";
        String sFilename = request.getHeader("file-name");
        String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
        sFilenameExt = sFilenameExt.toLowerCase();

        String[] whiteChk = {"jpg","png","bmp","gif"};
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/fileDownload/{id}")
    public void fileDownlaod(@PathVariable ("id") Long fileId, HttpServletResponse response) {
        try {
            fileService.downloadFile(fileId, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
