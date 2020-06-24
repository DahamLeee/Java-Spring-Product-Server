package com.daham.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	private String index() {
		return "index";
	}
	
	@RequestMapping("/product")
	private String product() {
		return "product/list";
	}
}
