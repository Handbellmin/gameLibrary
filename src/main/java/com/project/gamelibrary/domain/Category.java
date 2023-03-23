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
    private List<Item> itemList = new ArrayList<>();

    @OneToOne(mappedBy = "category")
    private BoardCategory boardCategory;

    private String type;

    private String column01;
    private String column02;






}
