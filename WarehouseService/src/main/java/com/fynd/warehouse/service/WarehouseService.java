package com.fynd.warehouse.service;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public String store(Long productCode, String color) {
		
		if(warehouse.getCapacity()==Arrays.asList(warehouse.getRacks()).stream().filter(Objects::nonNull).count())
			return "Warehouse is full";
		
		Product product = new Product(productCode, color);
		Rack rack = new Rack(product);
		warehouse.getRacks()[WarehouseService.index] = rack;
		String response="Allocated slot number : " + (WarehouseService.index+1);
		
		for(int p=0;p<warehouse.getRacks().length;p++) {
			if(warehouse.getRacks()[p]==null) {
				WarehouseService.index=p;
				break;
			}
		}
		//System.out.println(Arrays.toString(warehouse.getRacks()));
		
		return response;
	}

	public String sell(Integer slotNo) {
		if(slotNo-1<warehouse.getCapacity()) {
			Rack rack = warehouse.getRacks()[slotNo-1];
			if(rack!=null) warehouse.getRacks()[slotNo-1]=null;
			//System.out.println(Arrays.toString(warehouse.getRacks()));
			
			return "Slot number "+(slotNo)+" is free";
		}
		//System.out.println(Arrays.toString(warehouse.getRacks()));
		
		return "Invalid slot : "+(slotNo);
	}

	public void status() {
		System.out.println("Slot No.\tProduct Code\tColour");
		Arrays.asList(warehouse.getRacks()).stream().filter(Objects::nonNull).forEach(rack -> System.out.println("\t"+(Arrays.asList(warehouse.getRacks()).indexOf(rack)+1)+"\t\t"+rack.getProduct().getProductCode()+"\t"+rack.getProduct().getColor()));
	}
	
	public String product_codes_for_products_with_colour(String color) {
		return Arrays.asList(warehouse.getRacks()).stream().filter(rack -> rack.getProduct().getColor().equals(color)).map(rack->rack.getProduct().getProductCode().toString()).collect(Collectors.joining(" , "));
	}
	
	public String slot_numbers_for_products_with_colour(String color) {
		Rack[] racks = warehouse.getRacks();
		String slotNumbers = "";
		for(int p=0;p<racks.length;p++) {
			if(racks[p]!=null && racks[p].getProduct().getColor().equals(color)) {
				slotNumbers+= String.valueOf(p+1)+",";
			}
		}
		return slotNumbers.substring(0, slotNumbers.length()-1);
	}

	public String slot_number_for_product_code(Long productCode) {
		Rack[] racks = warehouse.getRacks();
		for(int p=0;p<racks.length;p++) {
			if(racks[p]!=null && racks[p].getProduct().getProductCode().equals(productCode)) {
				 return String.valueOf(p+1);
			}
		}
		
		return "Not found";
	}

}
