package com.team.project.config;

import com.team.project.interceptor.LoggerInterceptor;
import com.team.project.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * configure interceptor
 *
 * @author ZQJ
 * @date 4/28/2020
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // interceptor for logger
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
        // interceptor for login
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/login");
    }
}
