package com.jjim.user.application;

import com.jjim.common.domain.SessionUser;
import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import com.jjim.user.domain.entity.User;
import com.jjim.user.infra.encrypt.PasswordVerificationService;
import com.jjim.user.infra.repository.AuthRepository;
import com.jjim.user.presentation.request.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordVerificationService passwordVerificationService;

    @Transactional(readOnly = true)
    public SessionUser signIn(final SignInRequest request) {
        final User user = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUNT));

        passwordVerificationService.verifyPassword(request.getPassword(), user.getPassword());

        return new SessionUser(user.getId(), user.getEmail());
    }

}
