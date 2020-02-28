package com.fynd.warehouse.model;

public class Warehouse {

	private Integer capacity;
	private Rack[] racks;
	
	public Warehouse(Integer capacity) {
		this.capacity=capacity;
		racks=new Rack[capacity];
	}
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Rack[] getRacks() {
		return racks;
	}

	public void setRacks(Rack[] racks) {
		this.racks = racks;
	}
	
}
