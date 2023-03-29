package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Files {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    private String originFileName;
    private String savedFileName;
    private String extension;

    private String uploadDir;

    private String contentType;

    private String filePath;
    private Long fileSize;
    private LocalDateTime regDate;



    public static class Builder {
        private final Board board;

        private final String uploadDir;
        private final String originFileName;
        private final String savedFileName;
        private final Long fileSize;

        private String extension;
        private String contentType;
        private LocalDateTime regDate;


        public Builder(Board board,String uploadDir, String originFileName,String savedFileName, Long fileSize) {
            this.board = board;
            this.uploadDir = uploadDir;
            this.originFileName = originFileName;
            this.fileSize = fileSize;
            this.savedFileName = savedFileName;
        }

        public Builder setExtension(String extension){
            this.extension = extension;
            return this;
        }
        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
        public Builder setRegDate(LocalDateTime regDate){
            this.regDate = regDate;
            return this;
        }
        public Files build() { return new Files(this);}
    }
    protected Files() {}
    private Files(Builder builder){
        this.board = builder.board;
        this.originFileName = builder.originFileName;
        this.fileSize = builder.fileSize;
        this.extension = builder.extension;
        this.contentType = builder.contentType;
        this.regDate = builder.regDate;
    }

    public void setBoard(Board board) {
        this.board = board;
        if(!board.getFiles().contains(this)){
            board.getFiles().add(this);
        }
    }
}
