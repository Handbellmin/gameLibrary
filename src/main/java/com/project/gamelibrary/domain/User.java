package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name= "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SubScribe> subScribeList = new ArrayList<>();

    private String userName;

    private String userNickName;

    private String password;

    private LocalDateTime createDate;

    @Embedded
    private Address address;

    private String user_roles;

}
