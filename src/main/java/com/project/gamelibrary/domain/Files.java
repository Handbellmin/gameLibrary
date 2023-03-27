package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Files {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    private String originFileName;

    private String filePath;
    private Long fileSize;

    public static class Builder {
        private final Board board;
        private final String originFileName;
        private final String filePath;
        private final Long fileSize;

        public Builder(Board board, String originFileName, String filePath, Long fileSize) {
            this.board = board;
            this.originFileName = originFileName;
            this.filePath = filePath;
            this.fileSize = fileSize;
        }
        public Files build() { return new Files(this);}
    }
    protected Files() {}
    private Files(Builder builder){
        this.board = builder.board;
        this.originFileName = builder.originFileName;
        this.filePath = builder.filePath;
        this.fileSize = builder.fileSize;
    }
    public void setBoard(Board board) {
        this.board = board;
        if(!board.getFiles().contains(this)){
            board.getFiles().add(this);
        }
    }


}
