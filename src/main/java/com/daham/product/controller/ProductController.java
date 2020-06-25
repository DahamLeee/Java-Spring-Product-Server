package com.daham.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping(value = "/mvwrite")
	private String mvwrite() {
		return "product/write";
	}
	
	@GetMapping(value = "/mvdetail/{id}")
	private String mvdetail(@PathVariable String id) {
		System.out.println("id : " + id);
		return "product/detail";
	}
}
