package com.daham.product.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daham.product.dto.UserDto;
import com.daham.product.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8080/daham/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
@Api(value="DAHAM")
public class UserRestController {
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "사용자가 새롭게 회원가입을 할 수 있도록 합니다.")
	@PostMapping(value="/join")
	private ResponseEntity<HashMap<String, Integer>> insert(@RequestBody UserDto userDto){
		HashMap<String, Integer> obj = new HashMap<>();
		
		int cnt = userService.insert(userDto);
		obj.put("status", cnt);
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자가 id, password를 통해 로그인을 합니다.", response = HashMap.class)
	@PostMapping(value="/login")
	private ResponseEntity<HashMap<String, UserDto>> login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session, @RequestParam(required = false) String idsave, @RequestParam String user_id, @RequestParam String password){
		HashMap<String, UserDto> obj = new HashMap<>();
		try {
			System.out.println(user_id + " " + password);
			UserDto userDto = userService.login(user_id, password);
			UserDto userdto = new UserDto();
			userdto.setUser_id("fail");
			if(userDto != null) {
				session.setAttribute("userinfo", userDto);
				userdto.setUser_id("success");
				obj.put("userinfo", userDto);
				obj.put("status", userdto);
				if("saveok".equals(idsave)) {
					Cookie cookie = new Cookie("saveid", userDto.getUser_id());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 3);
					response.addCookie(cookie);
				} else {
					Cookie[] cookies = request.getCookies();
					if(cookies != null) {
						Cookie cookie = new Cookie("saveid", null);
						cookie.setMaxAge(0);
						cookie.setPath(request.getContextPath());
						response.addCookie(cookie);
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
	
	@PostMapping(value = "/logout")
	private ResponseEntity<HashMap<String, Integer>> logout(HttpSession session){
		HashMap<String, Integer> obj = new HashMap<String, Integer>();
		session.invalidate();
		obj.put("res", 1);
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 정보를 수정합니다.")
	@PutMapping("/")
	private ResponseEntity<HashMap<String, Integer>> modify(Model model, HttpSession session, @RequestBody UserDto userDto) {
		session.setAttribute("userinfo", userDto);
		int cnt = userService.modify(userDto);
		HashMap<String, Integer> obj = new HashMap<>();
		if(cnt >= 1) {
			obj.put("status", 1);
		} else {
			obj.put("status", 0);
		}
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 정보를 삭제합니다 (탈퇴) ")
	@DeleteMapping("/")
	private ResponseEntity<HashMap<String, Integer>> delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody UserDto userDto){
		HashMap<String, Integer> obj = new HashMap<>();
		int cnt = userService.delete(userDto);
		if(cnt == 1) {
			session.invalidate();
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				Cookie cookie = new Cookie("saveid", null);
				cookie.setMaxAge(0);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			obj.put("status", 1);
		} else {
			obj.put("status", 0);
		}
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
}
