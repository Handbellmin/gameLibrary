package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.BoardForm;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.repository.FileRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
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

    public Optional<Files> findById(Long fileId) {
        return fileRepository.findById(fileId);
    }

    public void downloadFile(Long fileId, HttpServletResponse response) throws IOException {
        Optional<Files> file = findById(fileId);
        if(file.isPresent()){
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String filePath = absolutePath + "files" + File.separator +file.get().getRegDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String filename = file.get().getSavedFileName();
            File f = new File(filePath,filename);
            response.setContentType("application/download");
            response.setContentLength((int)f.length());
            response.setHeader("Content-disposition","attachment;filename=\"" + file.get().getOriginFileName() + "\"");
            OutputStream os = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(f);
            FileCopyUtils.copy(fileInputStream,os);
            fileInputStream.close();
            os.close();
        }
        else {
            log.error("파일다운로드 실패");
            return;
        }
    }
}
