package com.jjim.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionCode {

    EXISTS_USER_EMAIL(HttpStatus.BAD_REQUEST, 1000,  "이미 존재하는 이메일 입니다."),
    EXISTS_USER_NICKNAME(HttpStatus.BAD_REQUEST, 1001, "이미 존재하는 닉네임 입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 1002, "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUNT(HttpStatus.BAD_REQUEST, 1003, "존재하지 않는 유저입니다."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, 1004, "승인되지 않은 유저입니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    UserExceptionCode(
            final HttpStatus status,
            final int code,
            final String message
    ) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
