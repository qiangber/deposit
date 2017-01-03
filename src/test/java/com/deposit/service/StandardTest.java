package com.deposit.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deposit.entity.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml", "classpath:applicationContext.xml"})
public class StandardTest {
	
	@Autowired
	private StandardService standardService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void add() {
		Standard s = new Standard();
		s.setName("铜");
		s.setExchange("上期所");
		s.setStandard(0.19);
		s.setUnit(10);
		standardService.add(s);
	}
	
	@Test
	public void delete() {
		standardService.deleteNotToday(new Date());
	}
	
	@Test
	public void getProperty() {
		List<String> list = standardService.getExchangeList();
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	@Test
	public void getProperty2() {
		List<String> list = standardService.getPropertyByParam("name", "郑商所");
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	@Test
	public void calculate() {
		Standard standard = new Standard();
		standard.setExchange("郑商所");
		standard.setName("棉一号");
		System.out.println(standardService.getDeposit(standard, 1, 1));
	}

}
