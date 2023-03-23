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

    public BoardCommentBuilder builder(String cmtContent) {
        if(cmtContent == null){
            throw new IllegalArgumentException("필수 파라미터 누락");
        }
        return new BoardCommentBuilder().cmtContent(cmtContent);
    }

}
