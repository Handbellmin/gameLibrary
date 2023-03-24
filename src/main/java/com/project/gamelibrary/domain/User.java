package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.*;

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

    private String userRole;
    public static class Builder {
        private final String userName;

        private final String password;

        private String userNickName;
        private LocalDateTime createDate;
        private String userRole;
        public Builder (String userName, String password){
            this.userName = userName;
            this.password = password;
        }
        public Builder setUserNickName(String userNickName) {
            this.userNickName = userNickName;
            return this;
        }
        public Builder setCreatDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }
        public Builder setUserRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    public User(Builder builder){
        this.userName = builder.userName;
        this.password = builder.password;
        this.userNickName = builder.userNickName;
        this.createDate = builder.createDate;
    }

}
