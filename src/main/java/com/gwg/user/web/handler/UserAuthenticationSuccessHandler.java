package com.gwg.user.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

//认证成功后把用户信息放到Session中
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("用户认证成功后的处理 start .....");
		//1.查询当前登录用户的用户信息
		//2.把用户信息放到Session中
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
