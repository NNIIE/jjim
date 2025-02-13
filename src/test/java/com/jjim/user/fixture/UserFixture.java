package com.jjim.user.fixture;

import com.jjim.user.domain.entity.User;
import com.jjim.user.presentation.request.SignOffRequest;
import com.jjim.user.presentation.request.SignUpRequest;
import com.jjim.user.presentation.request.UpdateUserRequest;

import java.util.UUID;

public class UserFixture {

    public static final UUID USER_ID = UUID.randomUUID();
    public static final String USER_EMAIL = "test@ably.com";
    public static final String USER_PASSWORD = "qwer1234!!";

    public static User createMockUser() {
        return User.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .build();
    }

    public static SignUpRequest createSignUpRequest() {
        return new SignUpRequest(USER_EMAIL, USER_PASSWORD);
    }

    public static SignOffRequest createSignOffRequest() {
        return new SignOffRequest(USER_PASSWORD);
    }

    public static UpdateUserRequest createUpdateUserRequest() {
        return new UpdateUserRequest(USER_PASSWORD);
    }

}
