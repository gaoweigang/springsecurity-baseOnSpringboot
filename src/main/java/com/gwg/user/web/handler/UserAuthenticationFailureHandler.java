package com.gwg.user.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//认证失败我们需要进行的处理
public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.info("用户认证失败后的处理 start .....");

		super.onAuthenticationFailure(request, response, exception);
	}
}
