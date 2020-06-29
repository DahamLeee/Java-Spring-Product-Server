package com.daham.product.dao;

import com.daham.product.dto.UserDto;

public interface UserDao {
	public UserDto login(String user_id, String password); // 로그인
	public int insert(UserDto userDto); // 회원 가입
	public int modify(UserDto userDto); // 회원 정보 수정
	public int delete(UserDto userDto); // 회원 정보 삭제 (탈퇴)
}
