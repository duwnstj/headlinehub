package com.sparta.headlinehub.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;



    public Comment(User user,String comment,Board board){
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.comment = comment;
        this.board = board;
   }
}
