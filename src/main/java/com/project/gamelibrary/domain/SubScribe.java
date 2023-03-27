package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class SubScribe {
    @Id @GeneratedValue
    @Column(name = "subscribe_id")
    private Long id;

    @ManyToOne(fetch =  LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    private LocalDateTime subDate;

    protected SubScribe () {}

    public SubScribe(Builder builder){
        this.user = builder.user;
        this.subDate = builder.subDate;
    }

    //== 연관 관계 메서드 ==//
    public void setUser(User user) {
        this.user = user;
        user.getSubScribeList().add(this);
    }

    //==생성 메서드==//
    public static class Builder {
        private final User user;
        private LocalDateTime subDate;

        public Builder(User user) {
            this.user = user;
        }

        public Builder setSubDate(LocalDateTime subDate) {
            this.subDate = LocalDateTime.now();
            return this;
        }
        public SubScribe build() {
            return new SubScribe(this);
        }
    }
}
