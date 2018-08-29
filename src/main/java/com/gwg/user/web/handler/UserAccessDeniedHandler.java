package com.gwg.user.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSON;
import com.gwg.user.common.Cost;
import com.gwg.user.common.entity.Result;
import com.gwg.user.dto.UserDto;
import com.gwg.user.util.SessionUtil;
import com.gwg.user.web.security.HttpForbiddenEntryPoint;

public class UserAccessDeniedHandler implements AccessDeniedHandler {
	
	private static Logger logger = LoggerFactory.getLogger(UserAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	logger.info("用户没权限访问 start .........");
    	HttpForbiddenEntryPoint.writeCrosInfo(response);
        Result<String> result = Result.error("600", "没有权限访问");
        UserDto userDto = SessionUtil.getSessionAttribute(Cost.SESSION_KEY_USER, request);
        if (null == userDto) {
            result = Result.error("610", "用户未登录");
        }
        JSON.writeJSONString(response.getWriter(), result);
    }
}
