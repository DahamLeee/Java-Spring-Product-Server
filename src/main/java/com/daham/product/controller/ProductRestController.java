package com.daham.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daham.product.dto.ProductDto;
import com.daham.product.dto.UserDto;
import com.daham.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8080/daham/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/product/api")
@Api(value="DAHAM")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "판매자가 등록한 상품들의 정보를 가지고 옴")
	@GetMapping(value="/searchAll")
	private ResponseEntity<List<ProductDto>> searchAll(){
		List<ProductDto> list = new ArrayList<>();
		list = productService.searchAll();
		return new ResponseEntity<List<ProductDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "판매자가 등록한 상품들의 정보를 가지고 옴")
	@PostMapping(value="/write")
	private ResponseEntity<HashMap<String, Integer>> write(HttpSession session, @RequestBody ProductDto productDto){
		HashMap<String, Integer> obj = new HashMap<>();
		UserDto temp = (UserDto) session.getAttribute("userinfo");
		productDto.setSeller(temp.getId());
		int cnt = productService.write(productDto);
		if(cnt == 1) {
			obj.put("status", 1);
		} else {
			obj.put("status", 0);
		}
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "판매자가 등록한 상품들의 정보를 가지고 옴")
	@GetMapping(value="/search")
	private ResponseEntity<HashMap<String, Integer>> search(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "") String key, @RequestParam(required = false, defaultValue = "") String word, @RequestParam(required = false, defaultValue = "1") int page){
		HashMap<String, Integer> obj = new HashMap<>();
		System.out.println("key : " + key);
		System.out.println("word : " + word);
		System.out.println("page : " + page);
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 id를 가지고 있는 상품의 정보를 가지고 옴")
	@GetMapping(value="/findProductById/{id}")
	private ResponseEntity<ProductDto> findProductById(@PathVariable String id){
		ProductDto productDto = productService.searchOne(id);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 id를 가지고 있는 상품의 정보를 가지고 옴")
	@DeleteMapping(value="/{id}")
	private ResponseEntity<HashMap<String, Integer>> deleteProduct(@PathVariable String id){
		int cnt = productService.remove(id);
		HashMap<String, Integer> obj = new HashMap<>();
		
		if(cnt == 1) { // 삭제 성공
			obj.put("status", 1);
		} else { // 삭제 실패
			obj.put("status", 0);
		}
		
		return new ResponseEntity<HashMap<String,Integer>>(obj, HttpStatus.OK);
	}
}
