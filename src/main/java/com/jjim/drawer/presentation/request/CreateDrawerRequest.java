package com.jjim.drawer.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import static com.jjim.common.utils.consts.RegexpConstants.ENGLISH_KOREAN_REGEXP;

@Getter
@ToString
public class CreateDrawerRequest {

    @NotBlank(message = "이름은 필수 입력 입니다.")
    @Pattern(regexp = ENGLISH_KOREAN_REGEXP, message = "이름은 한글 또는 영어이어야 합니다.")
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10자 이하여야 합니다.")
    private String name;

}
