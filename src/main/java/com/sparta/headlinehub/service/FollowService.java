package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.follow.request.PostFollowingSaveRequestDto;
import com.sparta.headlinehub.dto.follow.response.PostFollowingSaveResponseDto;
import com.sparta.headlinehub.entity.Follow;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import com.sparta.headlinehub.repository.FollowRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public PostFollowingSaveResponseDto saveFollowing(AuthUser authUser, PostFollowingSaveRequestDto requestDto) {
        // 본인 인증
        Long userId = authUser.getId();
        User my = findUser(userId);

        // 팔로잉 할 유저 찾기
        User user = findUser(requestDto.getId());

        // 팔로잉 Entity 저장 후 DB 저장
        Follow following = new Follow(my, user);
        followRepository.save(following);

        // DTO 변환
        PostFollowingSaveResponseDto responseDto = new PostFollowingSaveResponseDto(following);

        return responseDto;
    }

    /* 유저 ID 찾기 */
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFindException("유저를 찾을 수 없습니다.")
        );
    }
}
