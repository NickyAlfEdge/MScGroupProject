package com.team.project.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * login interceptor, if user hasn't login, redirect to login page
 *
 * @author ZQJ
 * @date 4/28/2020
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * <p>
     * Determine if the request is about to be handled by a mapping configured
     * by <mvc:resources>
     * </p>
     *
     * @param handler - the handler to inspect
     * @return - true if this is a <mvc:resources> mapped request, false
     * otherwise
     */
    private boolean isResourceHandler(Object handler) {
        return handler instanceof ResourceHttpRequestHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ignore static resources
        if (isResourceHandler(handler)) {
            return true;
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
