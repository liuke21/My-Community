package com.lk.InterCeptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration

public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer{
	
	@Autowired
	private SessionInterCeptor sessionInterCeptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(sessionInterCeptor).addPathPatterns("/**");
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry1){
		registry1.addResourceHandler("/file/upload/**").addResourceLocations("D:/Communtiy/"); 
	}
}

