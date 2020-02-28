package com.fynd.warehouse.model;

public class Product {
	
	private Long productCode;
	private String color;
	
	public Product(Long productCode, String color) {
		super();
		this.productCode = productCode;
		this.color = color;
	}
	
	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", color=" + color + "]";
	}


}
