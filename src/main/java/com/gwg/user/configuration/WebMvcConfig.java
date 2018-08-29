package com.gwg.user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 继承WebMvcConfigurerAdapter采用JavaBean形式实现个性化配置定制。
 * WebMvcConfigureAdapter该抽象类里面其实没有任何的方法实现，只是空实现了接口WebMvcConfigurer内的全部方法，并没有给出任何的业务
 * 逻辑处理
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
		
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		/**
		 * 配置访问服务器根路径的时候 则跳转到index.html页面，而index.html在具体在那个目录里面，则是由ResourceHandlerRegistry配置的。
		 */
        registry.addViewController("/").setViewName("forward:/index.html");
    }
	
	/**
	 * 配置静态资源
	 * Spring boot有提供默认的资源映射
	 * https://www.cnblogs.com/java-synchronized/p/7091723.html
	 * http://blog.csdn.net/xichenguan/article/details/52794862
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/"); //访问路径为http://localhost:8088/assets/login.html
		registry.addResourceHandler("/**").addResourceLocations("classpath:/assets/"); //访问路径为http://localhost:8088/login.html
	}
	
	
}
