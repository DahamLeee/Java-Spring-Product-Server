package com.daham.product.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daham.product.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	SqlSession session;
	
	@Override
	public UserDto login(String id, String password) {
		return session.getMapper(UserDao.class).login(id, password);
	}

	@Override
	public void insert(UserDto userDto) {
		session.getMapper(UserDao.class).insert(userDto);
	}

}
