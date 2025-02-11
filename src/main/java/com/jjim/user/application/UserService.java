package com.jjim.user.application;

import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import com.jjim.user.domain.entity.User;
import com.jjim.user.infra.encrypt.PasswordEncoder;
import com.jjim.user.infra.encrypt.PasswordVerificationService;
import com.jjim.user.infra.repository.UserRepository;
import com.jjim.user.presentation.request.SignOffRequest;
import com.jjim.user.presentation.request.SignUpRequest;
import com.jjim.user.presentation.request.UpdateUserRequest;
import com.jjim.user.presentation.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordVerificationService passwordVerificationService;

    @Transactional
    public void signUp(final SignUpRequest request) {
        existsByEmail(request.getEmail());

        final User user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(final UUID id) {
        final User user = getUserById(id);

        return new UserProfileResponse(
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    @Transactional
    public void profileUpdate(
            final UUID id,
            final UpdateUserRequest request
    ) {
        final User user = getUserById(id);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    @Transactional
    public void signOff(
            final UUID id,
            final SignOffRequest request
    ) {
        final User user = getUserById(id);
        passwordVerificationService.verifyPassword(request.getPassword(), user.getPassword());

        userRepository.delete(user);
    }

    private User getUserById(final UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserException(UserExceptionCode.USER_NOT_FOUNT));
    }

    private void existsByEmail(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException(UserExceptionCode.EXISTS_USER_EMAIL);
        }
    }

}
