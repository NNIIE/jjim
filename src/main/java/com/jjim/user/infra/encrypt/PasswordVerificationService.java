package com.jjim.user.infra.encrypt;

import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordVerificationService {

    private final PasswordEncoder passwordEncoder;

    public void verifyPassword(final String requestPassword, final String userPassword) {
        if (!passwordEncoder.verifyPassword(requestPassword, userPassword)) {
            throw new UserException(UserExceptionCode.INVALID_PASSWORD);
        }
    }

}
