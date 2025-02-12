package com.jjim.common.exception.favorite;

import lombok.Getter;

@Getter
public class FavoriteException extends RuntimeException {

    private final FavoriteExceptionCode code;

    public FavoriteException(final FavoriteExceptionCode code) {
        this.code = code;
    }

}
