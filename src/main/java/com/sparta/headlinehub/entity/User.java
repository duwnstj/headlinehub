package com.sparta.headlinehub.entity;

import com.sparta.headlinehub.dto.user.request.PostUserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User extends Timestamped {
    @Column(name = "email", unique = true)
    private String email;
    private String pw;
    private String userName;

    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    /* 게시판 */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    /* 팔로잉 */
    @OneToMany(mappedBy = "following", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> followingList = new ArrayList<>();

    /* 팔로워 */
    @OneToMany(mappedBy = "follower", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> followerList = new ArrayList<>();

    /* 댓글 */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    public User(PostUserSaveRequestDto requestDto, String pw) {
        this.email = requestDto.getEmail();
        this.pw = pw;
        this.userName = requestDto.getUserName();
        this.phoneNumber = requestDto.getPhoneNumber();
    }

    public void update(String pw) {
        this.pw = pw;
    }
}
