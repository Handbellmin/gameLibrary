package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.Form.FileForm;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    @Value("${upload.path}")
    private String uploadDir;

    private final FileRepository fileRepository;
    public List<Files> findByboardId(Long boardId) {
        return fileRepository.findByBoardId(boardId);
    }

    @Transactional
    public void saveFile(BoardForm boardForm) throws Exception {

    }
    @Transactional
    public Long insertFile(Files files){
        return fileRepository.save(files).getId();
    }
}
