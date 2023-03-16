package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BoardComment {
    @Id @GeneratedValue
    @Column(name="boardComment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String cmtTtl;

    private String cmtContent;

    private String createId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
