package com.daham.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value = "/mvlogin", method = RequestMethod.GET)
	private String mvlogin() {
		return "user/login";
	}
	
	@RequestMapping(value = "/mvinsert", method = RequestMethod.GET)
	private String mvinsert() {
		return "user/join";
	}

	@RequestMapping(value = "/mvmodify", method = RequestMethod.GET)
	private String mvmodify() {
		return "user/modify";
	}
}
