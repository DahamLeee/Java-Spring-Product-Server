package com.daham.product.dao;

import java.util.List;

import com.daham.product.dto.ProductDto;

public interface ProductDao {
	public List<ProductDto> searchAll();
	public int write(ProductDto productDto);
	public ProductDto searchOne(String id);
	public int remove(String id);
}
