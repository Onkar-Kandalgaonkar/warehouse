package com.fynd.warehouse.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.fynd.warehouse.model.Product;
import com.fynd.warehouse.model.Rack;
import com.fynd.warehouse.model.Warehouse;

@Service
public class WarehouseService {

	private Warehouse warehouse;
	static int index = 0;

	public Warehouse create(Integer capacity) {
		warehouse = new Warehouse(capacity);
		return warehouse;
	}

	public String store(Long productCode, String color) {
		Product product = new Product(productCode, color);
		Rack rack = new Rack(product);
		
		warehouse.getRacks()[WarehouseService.index] = rack;
		String response="Allocated slot number : " + WarehouseService.index;
		
		for(int p=0;p<warehouse.getRacks().length;p++) {
			if(warehouse.getRacks()[p]==null) {
				WarehouseService.index=p;
				break;
			}
		}
		System.out.println(Arrays.toString(warehouse.getRacks()));
		return response;
	}

	public String sell(Integer slotNo) {
		if(slotNo<warehouse.getCapacity()) {
			Rack rack = warehouse.getRacks()[slotNo];
			if(rack!=null) warehouse.getRacks()[slotNo]=null;
			System.out.println(Arrays.toString(warehouse.getRacks()));
			return "Slot number "+slotNo+" is free";
		}
		System.out.println(Arrays.toString(warehouse.getRacks()));
		return "Invalid slot : "+slotNo;
	}

}
