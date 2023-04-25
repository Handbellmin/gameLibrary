package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.BoardComment;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BoardCommentForm {
    private Long boardId;
    private String cmtContent;
    private String createId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
