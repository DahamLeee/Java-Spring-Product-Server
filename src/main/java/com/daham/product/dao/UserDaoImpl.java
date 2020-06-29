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
	public UserDto login(String user_id, String password) {
		return session.getMapper(UserDao.class).login(user_id, password);
	}

	@Override
	public int insert(UserDto userDto) {
		return session.getMapper(UserDao.class).insert(userDto);
	}

	@Override
	public int modify(UserDto userDto) {
		return session.getMapper(UserDao.class).modify(userDto);
	}

	@Override
	public int delete(UserDto userDto) {
		return session.getMapper(UserDao.class).delete(userDto);
	}

}
