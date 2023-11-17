package com.helmes.sectorapi.session.internal;

import com.helmes.sectorapi.session.api.Session;
import com.helmes.sectorapi.session.api.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private static final String SESSION_KEY = "TASK_SESSION";

    private final HttpServletRequest request;

    @Override
    public Session getSession() {
        return (Session) request.getSession().getAttribute(SESSION_KEY);
    }

    @Override
    public boolean createSession(HttpServletResponse response) {
        Cookie cookie = getSessionCookie(request.getCookies());

        if (cookie == null) {
            cookie = new Cookie(SESSION_KEY, UUID.randomUUID().toString());
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }

        request.getSession().setAttribute(SESSION_KEY, new Session(getSessionId(cookie)));

        return true;
    }

    private UUID getSessionId(Cookie cookie) {
        return UUID.fromString(cookie.getValue());
    }

    private Cookie getSessionCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SESSION_KEY.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
