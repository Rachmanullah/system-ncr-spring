package com.example.ncrsystem.ncrsystem.config;

import com.example.ncrsystem.ncrsystem.middleware.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoggingInterceptor
            loggingInterceptor;

    public WebMvcConfig(
            LoggingInterceptor loggingInterceptor) {

        this.loggingInterceptor =
                loggingInterceptor;
    }

    @Override
    public void addInterceptors(
            InterceptorRegistry registry) {

        registry.addInterceptor(
                loggingInterceptor);
    }
}
