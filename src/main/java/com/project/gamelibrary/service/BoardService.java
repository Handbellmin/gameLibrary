package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.Handler.FileHandler;
import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.repository.BoardRepository;
import com.project.gamelibrary.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final FileHandler fileHandler;
    @Transactional
    public void saveBoard(BoardForm boardForm, List<MultipartFile> files,String removeFile) throws Exception {
        // Insert
        Board board = boardForm.toEntity();
        Long boardId;
        if (boardForm.getId() != null ) {
            boardId = boardForm.getId();
            Optional<Board> asBoard = findOne(boardForm.getId());
            asBoard.get().update(boardForm.getTtl(), boardForm.getContent(), boardForm.getPopupYn());
        } else {
            Board board1 = boardRepository.save(boardForm.toEntity());
            boardId = board1.getId();
        }
        if (removeFile != null && !removeFile.equals("")){
            Arrays.stream(removeFile.split(","))
                    .forEach(e->fileRepository.deleteById(Long.parseLong(e)));
        }
        List<Files> fileList = fileHandler.parseFileInfo(files);
        board = findOne(boardId).get();
        if(!fileList.isEmpty()) {
            for (Files file : fileList) {
                board.addFiles(file);
                fileRepository.save(file);
            }
        }
    }

    public Page<Board> findAllPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,Sort.by(Sort.Direction.ASC,("id")));
        Page<Board> boardPage = boardRepository.findAll(pageRequest);
        return boardPage;
    }

    public Optional<Board> findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
