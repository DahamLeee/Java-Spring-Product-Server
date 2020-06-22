package com.daham.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daham.product.dto.UserDto;
import com.daham.product.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/mvlogin", method = RequestMethod.GET)
	private String mvlogin() {
		return "user/login";
	}
	
	@RequestMapping(value = "/mvinsert", method = RequestMethod.GET)
	private String mvinsert() {
		return "user/join";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	private String insert(@RequestParam String j_userid, @RequestParam String j_password, @RequestParam String j_name, @RequestParam String j_phone) {
		UserDto userDto = new UserDto(j_userid, j_password, j_name, j_phone);
		userService.insert(userDto);
		return "index";
	}
}
