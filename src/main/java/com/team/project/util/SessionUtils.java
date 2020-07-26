package com.team.project.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZQJ
 * @date 5/12/2020
 */
public class SessionUtils {

    public static Object getAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }
}
