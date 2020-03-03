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
	public void testProductCodesForProductsWithColour() {
		initializeWarehouseWithProducts();
		assertEquals(Long.parseLong(warehouseService.productCodesForProductsWithColour("White")),72527273070L);
	}	
	
	@Test
	public void negativeTestProductCodesForProductsWithColour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.productCodesForProductsWithColour("Purple"),"");
	}
	
	@Test
	public void testSlotNumbersForProductsWithColour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slotNumbersForProductsWithColour("Green"),"1");
	}
	
	@Test
	public void negativeTestSlotNumbersForProductsWithColour() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slotNumbersForProductsWithColour("Purple"),"");
	}
	
	@Test
	public void testSlotNumberForProductCode() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slotNumberForProductCode(72527273071L),"1");
	}
	
	@Test
	public void negativeTestSlotNumberForProductCode() {
		initializeWarehouseWithProducts();
		assertEquals(warehouseService.slotNumberForProductCode(72527273074L),"Not found");
	}
	
}
