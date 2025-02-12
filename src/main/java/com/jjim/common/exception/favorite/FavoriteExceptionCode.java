package com.jjim.common.exception.favorite;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FavoriteExceptionCode {

    EXISTS_FAVORITE(HttpStatus.CONFLICT, 3000,  "이미 존재하는 찜 입니다."),
    FAVORITE_NOT_FOUNT(HttpStatus.NOT_FOUND, 3001, "존재하지 않는 찜 입니다."),
    FAVORITE_NOT_MATCH_USER(HttpStatus.UNAUTHORIZED, 3002, "자신의 찜만 삭제할 수 있습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    FavoriteExceptionCode(
            final HttpStatus status,
            final int code,
            final String message
    ) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
