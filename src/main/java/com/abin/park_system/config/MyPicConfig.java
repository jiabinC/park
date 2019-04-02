package com.abin.park_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image-upload/**").addResourceLocations("file:G:/毕业设计/park_system/src/main/resources/static/image-upload/");
}
}
