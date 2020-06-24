package com.daham.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daham.product.dto.ProductDto;
import com.daham.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8080/daham/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/product")
@Api(value="DAHAM")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "판매자가 등록한 상품들의 정보를 가지고 옴")
	@GetMapping(value="/searchAll")
	private ResponseEntity<List<ProductDto>> searchAll(){
		List<ProductDto> list = new ArrayList<>();
		list = productService.searchAll();
		return new ResponseEntity<List<ProductDto>>(list, HttpStatus.OK);
	}
}
