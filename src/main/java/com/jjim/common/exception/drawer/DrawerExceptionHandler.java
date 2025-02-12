package com.jjim.common.exception.drawer;

import com.jjim.common.exception.GlobalExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DrawerExceptionHandler {

    @ExceptionHandler(DrawerException.class)
    public ResponseEntity<GlobalExceptionResponse> drawerExceptionHandler(final DrawerException ex, final HttpServletRequest request) {
        final GlobalExceptionResponse response = new GlobalExceptionResponse(
            ex.getCode().getCode(),
            ex.getCode().getMessage()
        );

        log.warn("[DrawerException] URI: {}, CODE: {}", request.getRequestURI(), response.code());

        return ResponseEntity
            .status(ex.getCode().getStatus())
            .body(response);
    }

}
