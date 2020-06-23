package com.daham.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daham.product.dao.UserDao;
import com.daham.product.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	
	@Override
	public UserDto login(String id, String password) {
		return dao.login(id, password);
	}

	@Override
	public int insert(UserDto userDto) {
		return dao.insert(userDto);
	}

	@Override
	public int modify(UserDto userDto) {
		return dao.modify(userDto);
	}

	@Override
	public int delete(UserDto userDto) {
		return dao.delete(userDto);
	}

}
