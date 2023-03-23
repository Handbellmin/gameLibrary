package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class BoardCategory {
    @Id @GeneratedValue
    @Column(name="boardCategory_id")
    private Long id;

    @OneToOne

    @OneToMany(mappedBy = "boardCategory")
    private List<Board> board = new ArrayList<>();

    private LocalDateTime createDate;

}
