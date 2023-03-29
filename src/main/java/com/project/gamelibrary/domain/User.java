package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name= "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SubScribe> subScribeList = new ArrayList<>();

    private String username;

    private String userNickName;

    private String password;

    private String email;

    private LocalDateTime createDate;

    @Embedded
    private Address address;

    private String provider;
    private String providerId;

    private String userRole;
    public static class Builder {
        private final String username;

        private final String password;

        private String email;
        private String userNickName;
        private LocalDateTime createDate;
        private String userRole;
        
        //Oauth2.0 로그인
        private String provider;
        private String providerId;
        public Builder (String username, String password){
            this.username = username;
            this.password = password;
        }
        public Builder setUserNickName(String userNickName) {
            this.userNickName = userNickName;
            return this;
        }
        public Builder setCrateDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }
        public Builder setUserRole(String userRole) {
            this.userRole = userRole;
            return this;
        }
        public Builder setProvider(String provider){
            this.provider = provider;
            return this;
        }
        public Builder setProviderId(String providerId) {
            this.providerId = providerId;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    public User(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
        this.userNickName = builder.userNickName;
        this.createDate = builder.createDate;
        this.userRole = builder.userRole;
        this.provider = builder.provider;
        this.providerId = builder.providerId;
        this.email = builder.email;
    }

}
