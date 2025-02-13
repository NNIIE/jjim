package com.jjim.user.application;

import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import com.jjim.common.BaseUnitTest;
import com.jjim.user.domain.entity.User;
import com.jjim.user.fixture.UserFixture;
import com.jjim.user.infra.encrypt.PasswordVerificationService;
import com.jjim.user.infra.repository.UserRepository;
import com.jjim.user.presentation.request.SignOffRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignOffTest extends BaseUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordVerificationService passwordVerificationService;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원 탈퇴 성공")
    void sign_off_success() {
        // Given
        User user = UserFixture.createMockUser();
        SignOffRequest request = UserFixture.createSignOffRequest();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(user));
        doNothing().when(passwordVerificationService).verifyPassword(UserFixture.USER_PASSWORD, UserFixture.USER_PASSWORD);

        // When
        userService.signOff(UserFixture.USER_ID, request);

        // Then
        verify(userRepository).delete(user);
    }

    @Test
    @DisplayName("회원 탈퇴 실패 - 비밀번호 불일치")
    void sign_off_fail_invalid_password() {
        // Given
        User user = UserFixture.createMockUser();
        SignOffRequest request = UserFixture.createSignOffRequest();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(user));

        doThrow(new UserException(UserExceptionCode.INVALID_PASSWORD))
            .when(passwordVerificationService).verifyPassword(UserFixture.USER_PASSWORD, UserFixture.USER_PASSWORD);

        // When Then
        UserException exception = assertThrows(UserException.class,
            () -> userService.signOff(UserFixture.USER_ID, request));
        assertAll(
            () -> assertEquals(UserExceptionCode.INVALID_PASSWORD, exception.getCode()),
            () -> verify(userRepository, never()).delete(user)
        );
    }

    @Test
    @DisplayName("회원 탈퇴 실패 - 존재하지 않는 회원")
    void sign_off_fail_user_not_found() {
        // Given
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.empty());
        SignOffRequest request = UserFixture.createSignOffRequest();

        // When Then
        UserException exception = assertThrows(UserException.class,
            () -> userService.signOff(UserFixture.USER_ID, request));
        assertEquals(UserExceptionCode.USER_NOT_FOUNT, exception.getCode());
    }

}
