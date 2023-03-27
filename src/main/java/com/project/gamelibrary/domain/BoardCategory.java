package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class BoardCategory {
    @Id @GeneratedValue
    @Column(name="boardCategory_id")
    private Long id;

    @OneToMany(mappedBy = "boardCategory")
    private List<Board> board = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createDate;


}
