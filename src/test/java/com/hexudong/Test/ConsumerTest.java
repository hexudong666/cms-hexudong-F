package com.hexudong.Test;

import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class ConsumerTest {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:consumer.xml");
	}
}
