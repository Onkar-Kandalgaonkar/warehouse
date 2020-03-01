package com.fynd.warehouse.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestWarehouseService {

	@TestConfiguration
	static class UserServiceTestConfiguration {

		@Bean
		public WarehouseService warehouseService() {
			return new WarehouseService();
		}
	}

	@Autowired
	WarehouseService warehouseService;

	@Test
	public void testInitWareHouse() {
		warehouseService.create(6);
		Assert.assertEquals(warehouseService.getWarehouse().getCapacity(), Integer.valueOf(6));
	}
}
