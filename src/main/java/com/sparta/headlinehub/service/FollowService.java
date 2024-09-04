package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.follow.request.PostFollowingSaveRequestDto;
import com.sparta.headlinehub.dto.follow.response.GetFollowerSimpleResponseDto;
import com.sparta.headlinehub.dto.follow.response.GetFollowingSimpleResponseDto;
import com.sparta.headlinehub.dto.follow.response.PostFollowingSaveResponseDto;
import com.sparta.headlinehub.entity.Follow;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.follow.DuplicateFollowingException;
import com.sparta.headlinehub.exception.follow.WrongFollowingException;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import com.sparta.headlinehub.repository.FollowRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    /* 팔로잉 기능 */
    public PostFollowingSaveResponseDto saveFollowing(AuthUser authUser, PostFollowingSaveRequestDto requestDto) {
        // 본인 인증
        Long userId = authUser.getId();
        User my = findUser(userId);

        // 팔로잉 할 유저 찾기
        User user = findUser(requestDto.getId());

        // 로그인한 본인을 팔로잉 하는지 검사
        if (my.getId().equals(user.getId())) {
            throw new WrongFollowingException("잘못된 팔로잉 입니다.");
        }

        // 이미 팔로잉 했는지 검사
        if (checkRedundancy(my.getId(), user.getId())) {
            throw new DuplicateFollowingException("이미 팔로잉한 유저 입니다.");
        }

        // 팔로잉 Entity 저장 후 DB 저장
        Follow following = new Follow(my, user);
        followRepository.save(following);

        // DTO 변환
        PostFollowingSaveResponseDto responseDto = new PostFollowingSaveResponseDto(following);

        return responseDto;
    }

    /* 팔로잉 조회 */
    public List<GetFollowingSimpleResponseDto> getFollowing(AuthUser authUser) {
        // 유저 인증
        User user = findUser(authUser.getId());

        // 내가 팔로우 한 유저 가져오기
        List<Follow> follows = followRepository.findAllByFollowingId(user.getId());

        // DTO 변환
        List<GetFollowingSimpleResponseDto> responseDtos = follows
                .stream()
                .map(GetFollowingSimpleResponseDto::new)
                .toList();

        return responseDtos;
    }

    /* 팔로워 조회 */
    public List<GetFollowerSimpleResponseDto> getFollower(AuthUser authUser) {
        // 유저 인증
        User user = findUser(authUser.getId());

        // 나를 팔로우 한 유저 가져오기
        List<Follow> follows = followRepository.findAllByFollowerId(user.getId());

        // DTO 변환
        List<GetFollowerSimpleResponseDto> responseDtos = follows
                .stream()
                .map(GetFollowerSimpleResponseDto::new)
                .toList();

        return responseDtos;
    }

    /* 언팔로우 */
    public Long deleteFollow(AuthUser authUser, Long followId) {
        // 유저 인증
        User my = findUser(authUser.getId());

        // 언팔로우 할 유저
        User user = findUser(followId);

        // 팔로우 되어 있는지 확인
        if(!checkRedundancy(my.getId(), user.getId())) {
            throw new IllegalArgumentException("팔로우 되어 있지 않습니다.");
        }

        // entity 객체 가져오기
        Follow follow = new Follow(my, user);

        //삭제
        followRepository.delete(follow);

        return follow.getId();
    }

    /* 유저 ID 찾기 */
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFindException("유저를 찾을 수 없습니다.")
        );
    }

    /* 이미 팔로잉 했는지 검사 */
    private boolean checkRedundancy(Long myId, Long userId) {
        return followRepository.existsByFollowingIdAndFollowerId(myId, userId);
    }
}
