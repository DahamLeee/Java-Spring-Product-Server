package com.daham.product.service;

import java.util.List;

import com.daham.product.dto.ProductDto;

public interface ProductService {
	public List<ProductDto> searchAll();
	public int write(ProductDto productDto);
}
