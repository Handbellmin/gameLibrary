package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class PriorityGame {
    @Id @GeneratedValue
    @Column(name="priorityGame_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User user;

    @OneToOne(fetch = LAZY)
    private Game game;

    @OneToMany(mappedBy = "priorityGame")
    private List<Category> categoryList = new ArrayList<>();

    private LocalDateTime createDate;

    @Builder
    public void createPriorityGame () {

    }
}
