package com.team.project.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * print out request parameters
 *
 * @author ZQJ
 * @date 4/28/2020
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI() + getParameters(request));
        return true;
    }

    private String getParameters(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> {
            sb.append(key).append("=").append(Arrays.toString(value)).append("&");
        });
        return sb.toString();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (ex != null) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
