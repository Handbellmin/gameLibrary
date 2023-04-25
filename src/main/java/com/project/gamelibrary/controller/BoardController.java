package com.project.gamelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.Handler.FileHandler;
import com.project.gamelibrary.config.auth.PrincipalDetails;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardComment;
import com.project.gamelibrary.service.BoardCategoryService;
import com.project.gamelibrary.service.BoardCommentService;
import com.project.gamelibrary.service.BoardService;
import com.project.gamelibrary.service.FileService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
        board.ifPresent(value -> model.addAttribute("boardForm", value));
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
    @RequestMapping(value="/singleImageUploader")
    public void smarteditorImageUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            //파일정보
            String sFileInfo = "";
            HttpServletRequest streamRequest = request;
            ServletInputStream inputStream = streamRequest.getInputStream();
            System.out.println("request : " + inputStream);
            System.out.println("request : " + inputStream.read());
            String filename = request.getHeader("file-name");
            //파일 확장자
            String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
            //확장자를소문자로 변경
            filename_ext = filename_ext.toLowerCase();

            //이미지 검증 배열변수
            String[] allow_file = {"jpg", "png", "bmp", "gif"};

            //돌리면서 확장자가 이미지인지
            int cnt = 0;
            for (int i = 0; i < allow_file.length; i++) {
                if (filename_ext.equals(allow_file[i])) {
                    cnt++;
                }
            }

            //이미지가 아님
            if (cnt == 0) {
                PrintWriter print = response.getWriter();
                print.print("NOTALLOW_" + filename);
                print.flush();
                print.close();
            } else {
                //이미지이므로 신규 파일로 디렉토리 설정 및 업로드
                //파일 기본경로
                String dftFilePath = request.getSession().getServletContext().getRealPath("/");
                //파일 기본경로 _ 상세경로
                String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator + "multiupload" + File.separator;
                System.out.println("filePath 경로 : " + filePath);
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String realFileNm = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String today = formatter.format(new java.util.Date());
                realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
                String rlFileNm = filePath + realFileNm;
                ///////////////// 서버에 파일쓰기 /////////////////
                //inputstream이 비어있음
                OutputStream os = new FileOutputStream(rlFileNm);
                int numRead;
                byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
                while ((numRead = request.getInputStream().read(b, 0, b.length)) != -1) {
                    os.write(b, 0, numRead);
                }


                os.flush();
                os.close();
                ///////////////// 서버에 파일쓰기 /////////////////

                // 정보 출력
                sFileInfo += "&bNewLine=true";
                // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
                sFileInfo += "&sFileName=" + filename;
                sFileInfo += "&sFileURL=" + "/resources/editor/multiupload/" + realFileNm;
                PrintWriter print = response.getWriter();
                print.print(sFileInfo);
                print.flush();
                print.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
