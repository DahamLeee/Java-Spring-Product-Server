package com.daham.product.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daham.product.dto.UserDto;
import com.daham.product.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login")
	private ResponseEntity<HashMap<String, UserDto>> login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session, @RequestParam(required = false) String idsave, @RequestParam String id, @RequestParam String password){
		HashMap<String, UserDto> obj = new HashMap<>();
		System.out.println("==========login=========");
		try {
			UserDto userDto = userService.login(id, password);
			UserDto userdto = new UserDto();
			userdto.setId("fail");
			if(userDto != null) {
				session.setAttribute("userinfo", userDto);
				userdto.setId("success");
				obj.put("userinfo", userDto);
				obj.put("status", userdto);
				if("saveok".equals(idsave)) {
					Cookie cookie = new Cookie("saveid", userDto.getId());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 3);
					response.addCookie(cookie);
				} else {
					Cookie[] cookies = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("saveid".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				obj.put("status", userdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<HashMap<String, UserDto>>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout")
	private ResponseEntity<HashMap<String, Integer>> logout(HttpSession session){
		HashMap<String, Integer> obj = new HashMap<String, Integer>();
		session.invalidate();
		obj.put("res", 1);
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
}
