package com.daham.product.dao;

import com.daham.product.dto.UserDto;

public interface UserDao {
	public UserDto login(String id, String password); // 로그인
	public void insert(UserDto userDto); // 회원 가입
}
