package com.daham.product.dto;

public class ProductDto {
	private int id;
	private String name;
	private String seller;
	private int price;
	private String description;
	private String regtime;
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", seller=" + seller + ", price=" + price + ", description="
				+ description + ", regtime=" + regtime + "]";
	}
	
}
