package com.jjim.common.exception.user;

import com.jjim.common.exception.GlobalExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<GlobalExceptionResponse> userExceptionHandler(final UserException ex, final HttpServletRequest request) {
        final GlobalExceptionResponse response = new GlobalExceptionResponse(
            ex.getCode().getCode(),
            ex.getCode().getMessage()
        );

        log.warn("[User Exception] URI: {}, CODE: {}", request.getRequestURI(), response.code());

        return ResponseEntity
            .status(ex.getCode().getStatus())
            .body(response);
    }

}
