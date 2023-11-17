package com.helmes.sectorapi.session.api;

import jakarta.servlet.http.HttpServletResponse;

public interface SessionService {
    Session getSession();
    boolean createSession(HttpServletResponse response);
}
