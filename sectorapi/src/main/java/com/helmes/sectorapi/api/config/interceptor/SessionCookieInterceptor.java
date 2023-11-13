package com.helmes.sectorapi.api.config.interceptor;

import com.helmes.sectorapi.domain.session.api.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class SessionCookieInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        return sessionService.createSession(response);
    }
}
