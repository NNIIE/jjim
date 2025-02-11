package com.jjim.user.presentation;


import com.jjim.common.annotation.CurrentUser;
import com.jjim.common.domain.SessionUser;
import com.jjim.user.application.AuthService;
import com.jjim.user.presentation.request.SignInRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.jjim.common.utils.consts.KeyConstants.SESSION_USER_KEY;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인")
    public void signIn(
            @RequestBody @Valid final SignInRequest request,
            final HttpSession session
    ) {
        final SessionUser sessionUser = authService.signIn(request);
        session.setAttribute(SESSION_USER_KEY, sessionUser);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그아웃")
    public void signOut(
            final HttpSession session,
            @Parameter(hidden = true) @CurrentUser final SessionUser user
    ) {
        session.invalidate();
    }

}
