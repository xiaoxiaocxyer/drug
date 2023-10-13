package cn.tedu.drug.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.drug.interceptor.LoginInterceptor;

/**
 * 配置拦截器信息
 * @author PHP
 *
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//黑名单---拦截路径---必须登录才能访问
		List<String> patterns = new ArrayList<String>();
		patterns.add("/**");
		//白名单---在黑名单范围内，不需要登录可以访问
		List<String> excludePatterns = new ArrayList<String>();
		//样式文件
		excludePatterns.add("/common/**");
		excludePatterns.add("/css/**");
		excludePatterns.add("/images/**");
		excludePatterns.add("/js/**");
		excludePatterns.add("/json/**");
		excludePatterns.add("/jsplug/**");
		excludePatterns.add("/javascript/**");
		//登录
		excludePatterns.add("/login.html");
		excludePatterns.add("/customer/login");
		excludePatterns.add("/employees/login");
		//注册
		excludePatterns.add("/register.html");
		excludePatterns.add("/customer/reg");
		
		registry.addInterceptor( new LoginInterceptor() )
			.addPathPatterns(patterns)
			.excludePathPatterns(excludePatterns);
		
	}
	
}
