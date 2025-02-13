package com.jjim.user.application;

import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import com.jjim.common.BaseUnitTest;
import com.jjim.user.domain.entity.User;
import com.jjim.user.fixture.UserFixture;
import com.jjim.user.infra.encrypt.PasswordEncoder;
import com.jjim.user.infra.repository.UserRepository;
import com.jjim.user.presentation.request.UpdateUserRequest;
import com.jjim.user.presentation.response.UserProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GetProfileTest extends BaseUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("프로필 조회 성공")
    void get_profile_success() {
        // Given
        User user = UserFixture.createMockUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(user));

        // When
        UserProfileResponse profile = userService.getProfile((UserFixture.USER_ID));

        // Then
        assertAll(
            () -> assertEquals(user.getEmail(), profile.email()),
            () -> assertEquals(user.getCreatedAt(), profile.createdAt()),
            () -> assertEquals(user.getUpdatedAt(), profile.updatedAt())
        );
    }

    @Test
    @DisplayName("프로필 조회 실패 - 존재하지 않는 회원")
    void get_profile_fail_user_not_found() {
        // Given
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.empty());

        // When Then
        UserException exception = assertThrows(UserException.class,
            () -> userService.getProfile(UserFixture.USER_ID));
        assertEquals(UserExceptionCode.USER_NOT_FOUNT, exception.getCode());

    }

    @Test
    @DisplayName("프로필 업데이트 성공")
    void profile_update_success() {
        // Given
        User user = UserFixture.createMockUser();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.of(user));

        UpdateUserRequest request = UserFixture.createUpdateUserRequest();
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedNewPassword");

        // When
        userService.profileUpdate(UserFixture.USER_ID, request);

        // Then
        assertEquals("encodedNewPassword", user.getPassword());
    }

    @Test
    @DisplayName("프로필 업데이트 실패 - 존재하지 않는 사용자")
    void profile_update_fail_user_not_found() {
        // Given
        UpdateUserRequest request = UserFixture.createUpdateUserRequest();
        when(userRepository.findById(UserFixture.USER_ID)).thenReturn(Optional.empty());

        // When Then
        UserException exception = assertThrows(UserException.class,
            () -> userService.profileUpdate(UserFixture.USER_ID, request));
        assertEquals(UserExceptionCode.USER_NOT_FOUNT, exception.getCode());
    }

}
