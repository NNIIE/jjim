package com.jjim.user.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignInRequest {

    @NotBlank(message = "이메일은 필수 입력 입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    private String password;

}
