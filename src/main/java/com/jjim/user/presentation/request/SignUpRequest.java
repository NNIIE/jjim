package com.jjim.user.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

import static com.jjim.common.utils.consts.RegexpConstants.EMAIL_REGEXP;
import static com.jjim.common.utils.consts.RegexpConstants.SPECIAL_CHARACTERS_REGEXP;

@Getter
@ToString
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 입력 입니다.")
    @Pattern(regexp = EMAIL_REGEXP, message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    @Pattern(regexp = SPECIAL_CHARACTERS_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
