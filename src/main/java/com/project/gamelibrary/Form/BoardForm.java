package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Board;
import com.project.gamelibrary.domain.BoardCategory;
import com.project.gamelibrary.domain.Files;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardForm {
    private Long id;
    @NotEmpty(message = "제목을 입력하세요.")
    private String ttl;
    @NotEmpty(message = "내용을 비울순 없습니다.")
    private String content;
    private String popupYn;
    private String createId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private BoardCategory boardCategory;
    private List<Files> files;

    public Board toEntity() {
        return new Board.Builder(ttl,content,createId,popupYn,files)
                .build();
    }

}
