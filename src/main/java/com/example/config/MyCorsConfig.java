package com.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


//跨域处理
@Configuration
public class MyCorsConfig {

    @Bean
    // 注意，这个方法名不是自己编的，上面还import org.springframework.web.filter.CorsFilter;
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        //允许http://localhost:8888来访问我们。configuration.addAllowedOrigin("*")就是允许任何人访问;
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://localhost:8889");


        configuration.setAllowCredentials(true);//允许cookie等等传过来
        configuration.addAllowedMethod("*");//允许所有的方法都能发过来
        configuration.addAllowedHeader("*");//允许所有请求头进来

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);//拦截所有


        return new CorsFilter(urlBasedCorsConfigurationSource);

    }
}
