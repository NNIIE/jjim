package com.jjim.common.interceptor;

import com.jjim.common.domain.SessionUser;
import com.jjim.common.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.jjim.common.utils.consts.KeyConstants.SESSION_USER_KEY;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (excludePath(request)) {
            return true;
        }

        final SessionUser sessionUser = SessionUtil.getUserFromSession(request.getSession());
        request.setAttribute(SESSION_USER_KEY, sessionUser);

        return true;
    }

    // 인터셉터 제외
    private boolean excludePath(HttpServletRequest request) {
        return request.getRequestURI().equals("/user") && request.getMethod().equals("POST");
    }

}
