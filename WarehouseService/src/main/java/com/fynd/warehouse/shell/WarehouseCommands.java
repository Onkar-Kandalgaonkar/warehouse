package com.fynd.warehouse.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.fynd.warehouse.model.Warehouse;
import com.fynd.warehouse.service.WarehouseService;

@ShellComponent
public class WarehouseCommands {

	private WarehouseService warehouseService;

	@Autowired
	public WarehouseCommands(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	@ShellMethod("Create a warehouse.")
	public void warehouse(Integer capacity) {
		Warehouse warehouse = warehouseService.create(capacity);
		System.out.println("Created a warehouse with " + warehouse.getCapacity() + " slots");
	}

	@ShellMethod("Create a warehouse.")
	public void store(Long productId, String color) {
		String response = warehouseService.store(productId, color);
		System.out.println(response);
	}

	@ShellMethod("Sell product.")
	public void sell(Integer slotNo) {
		String response = warehouseService.sell(slotNo);
		System.out.println(response);
	}

	@ShellMethod("Status.")
	public void status() {
		warehouseService.status();
	}

	@ShellMethod("product_codes_for_products_with_colour.")
	public void product_codes_for_products_with_colour(String color) {
		System.out.println(warehouseService.product_codes_for_products_with_colour(color));
	}
	
	@ShellMethod("slot_number_for_product_code.")
	public void slot_number_for_product_code(Long productCode) {
		System.out.println(warehouseService.slot_number_for_product_code(productCode));
	}
}
