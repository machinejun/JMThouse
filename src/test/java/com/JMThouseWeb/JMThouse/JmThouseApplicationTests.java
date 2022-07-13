package com.JMThouseWeb.JMThouse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.JMThouseWeb.JMThouse.model.House;
import com.JMThouseWeb.JMThouse.service.HouseService;

@SpringBootTest
class JmThouseApplicationTests {
	
	@Autowired
	HouseService houseService;
	
	@Transactional
	@Test
	void contextLoads() {
		List<House> h = houseService.findAllByHostId(2);
	}
	
	

}
