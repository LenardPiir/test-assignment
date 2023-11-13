package com.helmes.sectorapi.domain.session.api;

import jakarta.servlet.http.HttpServletResponse;

public interface SessionService {
    Session getSession();
    boolean createSession(HttpServletResponse response);
}
