package com.fynd.warehouse.model;

public class Rack {

	private Product product;

	public Rack(Product product) {
		super();
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return product.toString();
	}

}
