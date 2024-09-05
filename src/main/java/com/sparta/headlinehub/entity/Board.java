package com.sparta.headlinehub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String content;

    public Board(String title, String content , User user) {
        this.title = title;
        this.content =content;
        this.user = user;

    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
