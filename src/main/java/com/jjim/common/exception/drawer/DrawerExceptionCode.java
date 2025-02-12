package com.jjim.common.exception.drawer;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DrawerExceptionCode {

    EXISTS_DRAWER_NAME(HttpStatus.CONFLICT, 2000,  "이미 존재하는 찜서랍 이름 입니다."),
    DRAWER_NOT_FOUNT(HttpStatus.NOT_FOUND, 2001, "존재하지 않는 찜서랍 입니다."),
    DRAWER_NOT_MATCH_USER(HttpStatus.UNAUTHORIZED, 2002, "자신의 찜서랍만 삭제할 수 있습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    DrawerExceptionCode(
            final HttpStatus status,
            final int code,
            final String message
    ) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
