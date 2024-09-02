package com.sparta.headlinehub.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PostUserSaveRequestDto {
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$" ,message = "이메일 규칙에 맞게 입력해주세요.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,}$",
            message = "비밀번호 형식이 올바르지 않습니다. 최소 8자 이상, 대소문자, 숫자, 특수문자(?=.*[@$!%*?&#.~_-)가 1개씩 포함되어야 합니다.")
    private String pw;

    @NotEmpty(message = "이름을 입력하세요.")
    private String userName;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화 번호 형식이 맞지 않습니다.")
    private String phoneNumber;
}
