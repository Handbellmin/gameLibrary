package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.Files;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileForm {
    private Long id;
    private Board board;
    private String originFileName;
    private String savedFileName;
    private String uploadDir;

    private String extension;

    private Long fileSize;

    private String contentType;

    private LocalDateTime regDate;

    @Builder
    public FileForm(Long id,Board board, String originFileName, String savedFileName, String uploadDir, String extension, Long fileSize, String contentType, LocalDateTime regDate) {
        this.id = id;
        this.board = board;
        this.originFileName = originFileName;
        this.savedFileName = savedFileName;
        this.uploadDir = uploadDir;
        this.extension = extension;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.regDate = regDate;
    }
    public Files toEntity() {
        return new Files.Builder(board,uploadDir,originFileName, savedFileName,fileSize)
                .setExtension(extension)
                .setContentType(contentType)
                .setRegDate(regDate)
                .build();
    }
}
