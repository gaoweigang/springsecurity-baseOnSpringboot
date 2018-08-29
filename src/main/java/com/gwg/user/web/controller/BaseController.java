package com.gwg.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@RequestMapping("/login-success")
	public String success(){
		logger.info("重定向 success start .....");
		return "redirect:success.html";
	}

	@RequestMapping("/login-error")
	public String failure(){
		logger.info("重定向  failure start .....");
		return "redirect:failure.html";
	}
}
