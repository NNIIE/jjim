package com.jjim.user.presentation.response;

import java.time.LocalDateTime;

public record UserProfileResponse(
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
