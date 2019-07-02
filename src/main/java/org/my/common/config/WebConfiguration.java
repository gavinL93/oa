package org.my.common.config;

import org.my.common.interceptor.AuthenticationInterceptor;
import org.my.common.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	LogInterceptor logInterceptor;

	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor).addPathPatterns("/**");// 所有路径都被拦截
		registry.addInterceptor(authenticationInterceptor).addPathPatterns("/app/**");
	}
}