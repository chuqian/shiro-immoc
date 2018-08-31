package com.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlTest {

	@Test
	public void testJdbcTemplate(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		applicationContext.getBean("jdbcTemplate");
		
	}
	
	@Test
	public void testSessionDao(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		applicationContext.getBean("sessionManager");
	}
	
	@Test
	public void testCacheManager(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		applicationContext.getBean("cacheManager");
		applicationContext.getBean("securityManager");
	}
}
