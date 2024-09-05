package com.sparta.headlinehub.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "comment")
public class Comment extends Timestamped {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String userName;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;



    public Comment(User user,String comment,Board board){
        this.user = user;
        this.userName = user.getUserName();
        this.comment = comment;
        this.board = board;
   }
   public void update(String comment){
        this.comment = comment ;
   }
}
