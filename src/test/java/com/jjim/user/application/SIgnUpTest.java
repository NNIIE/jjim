package com.jjim.user.application;

import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import com.jjim.common.BaseUnitTest;
import com.jjim.user.domain.entity.User;
import com.jjim.user.fixture.UserFixture;
import com.jjim.user.infra.encrypt.PasswordEncoder;
import com.jjim.user.infra.repository.UserRepository;
import com.jjim.user.presentation.request.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SIgnUpTest extends BaseUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입 성공")
    void sign_up_success() {
        // Given
        SignUpRequest request = UserFixture.createSignUpRequest();
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        // When
        userService.signUp(request);

        // Then
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertAll(
            () -> assertEquals(request.getEmail(), savedUser.getEmail()),
            () -> assertEquals("encodedPassword", savedUser.getPassword())
        );
    }

    @Test
    @DisplayName("회원가입 실패 - 이미 존재하는 이메일")
    void sign_up_fail_exists_email() {
        // Given
        SignUpRequest request = UserFixture.createSignUpRequest();
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // When Then
        UserException exception = assertThrows(UserException.class,
            () -> userService.signUp(request));
        assertEquals(UserExceptionCode.EXISTS_USER_EMAIL, exception.getCode());
    }

}
