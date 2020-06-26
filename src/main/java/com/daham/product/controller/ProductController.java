package com.daham.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daham.product.dto.ProductDto;
import com.daham.product.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productSerivce;
	
	@GetMapping(value = "/mvwrite")
	private String mvwrite() {
		return "product/write";
	}
	
	@GetMapping(value = "/mvdetail/{id}")
	private String mvdetail(@PathVariable String id, Model model) {
		ProductDto productDto = productSerivce.searchOne(id);
		model.addAttribute("product", productDto);
		return "product/detail";
	}
}
