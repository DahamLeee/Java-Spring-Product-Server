package com.daham.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daham.product.dao.ProductDao;
import com.daham.product.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao dao;
	
	@Override
	public List<ProductDto> searchAll() {
		return dao.searchAll();
	}

	@Override
	public int write(ProductDto productDto) {
		return dao.write(productDto);
	}

}
