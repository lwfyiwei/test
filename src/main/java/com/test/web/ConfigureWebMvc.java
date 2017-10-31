package com.test.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class ConfigureWebMvc extends WebMvcConfigurerAdapter {
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		log.info("ConfigureInterceptors addInterceptors...");
		registry.addInterceptor(new CharacterValidInterceptors()).addPathPatterns("/**");
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new ArgumentResolver());
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
