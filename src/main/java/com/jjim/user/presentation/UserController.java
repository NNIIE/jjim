package com.jjim.user.presentation;

import com.jjim.common.annotation.CurrentUser;
import com.jjim.common.domain.SessionUser;
import com.jjim.user.application.UserService;
import com.jjim.user.presentation.request.SignOffRequest;
import com.jjim.user.presentation.request.SignUpRequest;
import com.jjim.user.presentation.request.UpdateUserRequest;
import com.jjim.user.presentation.response.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원 가입")
    public void signUp(@RequestBody @Valid final SignUpRequest request) {
        userService.signUp(request);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원 정보 조회")
    public UserProfileResponse getProfile(
            @Parameter(hidden = true) @CurrentUser final SessionUser user
    ) {
        return userService.getProfile(user.id());
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원 정보 변경")
    public void profileUpdate(
            @RequestBody @Valid final UpdateUserRequest request,
            @Parameter(hidden = true) @CurrentUser final SessionUser user
    ) {
        userService.profileUpdate(user.id(), request);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원 탈퇴")
    public void signOff(
            @RequestBody @Valid final SignOffRequest request,
            @Parameter(hidden = true) @CurrentUser final SessionUser user,
            final HttpSession session
    ) {
        userService.signOff(user.id(), request);
        session.invalidate();
    }

}
