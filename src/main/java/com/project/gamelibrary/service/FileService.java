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

    @Transactional
    public Map<String, Object> saveFile(BoardForm boardForm) throws Exception {
        List<MultipartFile> multipartFiles = boardForm.getMultipartFiles();
        Map<String, Object> result = new HashMap<>();

        List<Long> fileIds = new ArrayList<>();

        try{
            if (multipartFiles != null) {
                if (multipartFiles.size() > 0 && !multipartFiles.get(0).getOriginalFilename().equals("")){
                    for (MultipartFile file1 : multipartFiles) {
                        String originalFileName = file1.getOriginalFilename();
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        String savedFileName = UUID.randomUUID() + extension;

                        File targetFile = new File(uploadDir + savedFileName);

                        result.put("result", "FAIL");

                        FileForm fileForm = FileForm.builder()
                                        .board(boardForm.toEntity())
                                        .originFileName(originalFileName)
                                        .savedFileName(savedFileName)
                                        .uploadDir(uploadDir)
                                        .extension(extension)
                                        .fileSize(file1.getSize())
                                        .contentType(file1.getContentType())
                                        .regDate(LocalDateTime.now())
                                .build();

                        Long fileId = insertFile(fileForm.toEntity());
                        log.info("fileId={}", fileId);

                        try{
                            InputStream fileStream = file1.getInputStream();
                            FileUtils.copyInputStreamToFile(fileStream, targetFile);
                            fileIds.add(fileId);
                            result.put("fileIdxs", fileIds.toString());
                            result.put("result","OK");
                        } catch(Exception e) {
                            FileUtils.deleteQuietly(targetFile);
                            e.printStackTrace();
                            result.put("result","FAIL");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Transactional
    public Long insertFile(Files files){

        return fileRepository.save(files).getId();
    }
}
