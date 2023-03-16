package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    @OneToMany(mappedBy = "category")
    private List<Game> gameList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "priorityGame_id")
    private PriorityGame priorityGame;

    private String mainType;

    private String type1;
    private String type2;
    private String type3;

}
