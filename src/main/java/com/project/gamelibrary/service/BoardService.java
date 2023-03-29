package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.Handler.FileHandler;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.repository.BoardRepository;
import com.project.gamelibrary.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final FileHandler fileHandler;
    @Transactional
    public void saveBoard(BoardForm boardForm, List<MultipartFile> files) throws Exception {
        // Insert
        Board board = boardForm.toEntity();
        if (boardForm.getId() != null ) {
            Board asBoard = boardRepository.findOne(boardForm.getId());
            asBoard.update(boardForm.getTtl(), boardForm.getContent(), boardForm.getPopupYn());
        } else {
            boardRepository.save(boardForm.toEntity());
        }
        log.info("files :{[]}", files);
        List<Files> fileList = fileHandler.parseFileInfo(files);
        if(!fileList.isEmpty()) {
            for (Files file : fileList) {
                boardForm.toEntity().addFiles(fileRepository.save(file));
            }
        }
    }
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
    public Board findOne(Long boardId) {
        return boardRepository.findOne(boardId);
    }
}
