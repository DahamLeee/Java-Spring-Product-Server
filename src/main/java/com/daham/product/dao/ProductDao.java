package com.daham.product.dao;

import java.util.List;

import com.daham.product.dto.ProductDto;

public interface ProductDao {
	public List<ProductDto> searchAll();
}
