package com.daham.product.service;

import com.daham.product.dto.UserDto;

public interface UserService {
	public UserDto login(String id, String password); // 로그인
	public void insert(UserDto userDto); // 회원가입
	
}
