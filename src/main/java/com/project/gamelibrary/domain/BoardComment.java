package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class BoardComment {
    @Id @GeneratedValue
    @Column(name="boardComment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String cmtContent;

    private String createId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public static class Builder {
        private final String cmtContent;
        private String createId;
        private LocalDateTime createDate;
        private Board board;

        public Builder(String cmtContent) {
            this.cmtContent = cmtContent;
        }
        public Builder setCreateId(String createId){
            this.createId = createId;
            return this;
        }
        public Builder setCreateDate(LocalDateTime createDate){
            this.createDate = createDate;
            return this;
        }
        public Builder setBoard(Board board){
            this.board = board;
            return this;
        }
        public BoardComment build() { return new BoardComment(this);}
    }
    protected BoardComment() {}

    private BoardComment(Builder builder){
        this.cmtContent = builder.cmtContent;
        this.createId = builder.createId;
        this.createDate = builder.createDate;
    }
}
