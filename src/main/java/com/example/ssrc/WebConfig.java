package com.example.ssrc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/public/", "/images/", "/public", "classpath:/static/").setCachePeriod(31556926);
//        registry.addResourceHandler("/m/**").addResourceLocations("classpath:/m/").setCachePeriod(20);
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(20);
//        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(20);
//        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/").setCachePeriod(20);
//        registry.addResourceHandler("/public/template1/**").addResourceLocations("classpath:/template1/").setCachePeriod(20);

    }
}
