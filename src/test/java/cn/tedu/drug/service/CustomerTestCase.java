package cn.tedu.drug.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTestCase {
	
	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void javatest(){
		int i = 3;
		String str =  i == 1 ? "123":i == 2 ? "234":"345";
		System.err.println(str);
	}
	
	@Test
	public void reg() {
		Customer customer = new Customer();
		customer.setUsername("123");
		customer.setPassword("123");
		customer.setGender(0);
		customer.setPhone("1234567");
		customer.setEmail("13@qq.com");
		try {
			customerService.reg(customer);
			System.err.println("ok");
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
}
