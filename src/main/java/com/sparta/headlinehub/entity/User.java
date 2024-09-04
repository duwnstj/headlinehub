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
public class  User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;
    private String pw;
    private String userName;
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    List<Board> boardList = new ArrayList<>();

    /* 팔로잉 */
    @OneToMany(mappedBy = "following", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Follow> followingList = new ArrayList<>();

    /* 팔로워 */
    @OneToMany(mappedBy = "follower", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Follow> followerList = new ArrayList<>();

    public User(PostUserSaveRequestDto requestDto, String pw) {
        this.email = requestDto.getEmail();
        this.pw = pw;
        this.userName = requestDto.getUserName();
        this.phoneNumber = requestDto.getPhoneNumber();
    }

    public void update(String pw){
        this.pw = pw;
    }
}
