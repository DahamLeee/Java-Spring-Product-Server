package com.daham.product.service;

import com.daham.product.dto.UserDto;

public interface UserService {
	public UserDto login(String user_id, String password); // 로그인
	public int insert(UserDto userDto); // 회원가입
	public int modify(UserDto userDto); // 회원 정보 수정
	public int delete(UserDto userDto); // 회원 정보 삭제 (탈퇴)
}
