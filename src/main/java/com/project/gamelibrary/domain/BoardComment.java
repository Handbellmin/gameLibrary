package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderClassName = "BoardCommentBuilder")
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
        private final Board board;
        private final String cmtContent;
        private final String createId;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public Builder(Board board, String cmtContent, String createId, LocalDateTime createDate) {
            this.board = board;
            this.cmtContent = cmtContent;
            this.createId = createId;
            this.createDate = createDate;
        }

        public Builder setUpdateDate() {
            this.updateDate = LocalDateTime.now();
            return this;
        }
        public BoardComment build() {
            return new BoardComment(this);
        }
    }
    protected BoardComment() {}

    private BoardComment(Builder builder){
        this.board = builder.board;
        this.cmtContent = builder.cmtContent;
        this.createId = builder.createId;
        this.createDate = builder.createDate;
        this.updateDate = builder.updateDate;

    }

}
