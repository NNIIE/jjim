package com.jjim.common.utils;

import com.jjim.common.domain.SessionUser;
import com.jjim.common.exception.user.UserException;
import com.jjim.common.exception.user.UserExceptionCode;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import static com.jjim.common.utils.consts.KeyConstants.SESSION_USER_KEY;

@UtilityClass
public class SessionUtil {

    public static SessionUser getUserFromSession(final HttpSession session) {
        final SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION_USER_KEY);

        if (sessionUser == null) {
            throw new UserException(UserExceptionCode.UNAUTHORIZED_USER);
        }

        return sessionUser;
    }

}
