package com.daham.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daham.product.dto.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao{
	@Autowired
	SqlSession session;
	
	@Override
	public List<ProductDto> searchAll() {
		return session.getMapper(ProductDao.class).searchAll();
	}

	@Override
	public int write(ProductDto productDto) {
		return session.getMapper(ProductDao.class).write(productDto);
	}

	@Override
	public ProductDto searchOne(String id) {	
		return session.getMapper(ProductDao.class).searchOne(id);
	}
	
}
