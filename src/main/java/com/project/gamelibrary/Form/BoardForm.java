package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.BoardCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardForm {
    private Long id;
    private String ttl;
    private String content;
    private String popupYn;
    private String createId;
    private LocalDateTime createDate;
    private BoardCategory boardCategory;
}
