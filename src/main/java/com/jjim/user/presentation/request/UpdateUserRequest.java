package com.jjim.user.presentation.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

import static com.jjim.common.utils.consts.RegexpConstants.SPECIAL_CHARACTERS_REGEXP;

@Getter
@ToString
public class UpdateUserRequest {

    @Pattern(regexp = SPECIAL_CHARACTERS_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
