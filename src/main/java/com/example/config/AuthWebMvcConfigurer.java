package com.example.config;

import com.example.common.AuthHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: ZC0
 * @Date: 2023/05/08/15:22
 * @Description: 代码完成
 */
@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private AuthHandlerInterceptor authHandlerInterceptor;

    /**
     * 给除了 /login 的接口都配置拦截器,拦截转向到 authHandlerInterceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
//                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
}