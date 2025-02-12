package com.jjim.common.exception.drawer;

import lombok.Getter;

@Getter
public class DrawerException extends RuntimeException {

    private final DrawerExceptionCode code;

    public DrawerException(final DrawerExceptionCode code) {
        this.code = code;
    }

}
