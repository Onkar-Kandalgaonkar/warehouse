package com.fynd.warehouse.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fynd.warehouse.WarehouseServiceApplication;

//@SpringBootTest(classes = TestApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WarehouseServiceApplication.class})
public class WarehouseServiceTest {

	//If we do not want to run the test case as junit test uncomment @SpringBootTest and comment out below method
	@TestConfiguration
	static class WarehouseServiceTestConfiguration {

		@Bean
		public WarehouseService warehouseService() {
			return new WarehouseService();
		}
	}

	@Autowired
	WarehouseService warehouseService;
	
	private void initializeWarehouseWithProducts() {
		warehouseService.create(2);
		warehouseService.store(72527273070L,"White");
		warehouseService.store(72527273071L, "Green");
	}

	@Test
	public void testInitWareHouse() {
		Assert.assertNotNull(warehouseService.create(6));
		Assert.assertEquals(warehouseService.getWarehouse().getCapacity(), Integer.valueOf(6));
	}
	
	@Test
	public void testStore() {
		warehouseService.create(6);
		Assert.assertEquals(warehouseService.store(72527273070L,"White"),"Allocated slot number : 1");
		Assert.assertEquals(warehouseService.getWarehouse().getRacks()[0].getProduct().getProductCode(), Long.valueOf(72527273070L));
		Assert.assertEquals(warehouseService.getWarehouse().getRacks()[0].getProduct().getColor(),"White");
	}
	
	@Test
	public void negativeTestStore() {
		initializeWarehouseWithProducts();
		Assert.assertEquals(warehouseService.store(72527273071L, "Green"), "Warehouse is full");
		Assert.assertEquals(warehouseService.getWarehouse().getRacks()[0].getProduct().getColor(),"White");
	}
	
	@Test
	public void testSell() {
		warehouseService.create(6);
		warehouseService.store(72527273070L,"White");
		Assert.assertEquals(warehouseService.sell(1), "Slot number 1 is free");
		Assert.assertNull(warehouseService.getWarehouse().getRacks()[0]);
	}
	
	@Test
	public void negativeTestSell() {
		warehouseService.create(6);
		warehouseService.store(72527273070L,"White");
		Assert.assertEquals(warehouseService.sell(7), "Invalid slot : 7");
		warehouseService.sell(1);
		Assert.assertNull(warehouseService.getWarehouse().getRacks()[0]);
	}
	
	@Test
	public void testproduct_codes_for_products_with_colour() {
		initializeWarehouseWithProducts();
		assertEquals(Long.parseLong(warehouseService.product_codes_for_products_with_colour("White")),72527273070L);
	}	
	
	@Test
	public void negativeTestproduct_codes_for_products_with_colour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.product_codes_for_products_with_colour("Purple"),"");
	}
	
	@Test
	public void testslot_numbers_for_products_with_colour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slot_numbers_for_products_with_colour("Green"),"1");
	}
	
	@Test
	public void negativeTestslot_numbers_for_products_with_colour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slot_numbers_for_products_with_colour("Purple"),"");
	}
	
	@Test
	public void testslot_number_for_product_code() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slot_number_for_product_code(72527273070L),"1");
	}
	
	@Test
	public void negativeTestslot_number_for_product_code() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slot_number_for_product_code(72527273074L),"Not found");
	}
	
}
