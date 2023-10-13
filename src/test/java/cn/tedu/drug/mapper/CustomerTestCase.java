package cn.tedu.drug.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.CustomerDao;
import cn.tedu.drug.entity.Customer;
import cn.tedu.drug.entity.CustomerTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTestCase {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Test
	public void findByUidTest() {
		List<Customer> customer =  customerDao.findByUidUsername();
		for (Customer cus : customer) {
			System.err.println(cus);
		}
	}
	
	@Test
	public void delete() {
		//设置is_delete
		Integer isDelete = 1;
		String modifiedUser = "12345678";
		Date modifiedTime = new Date();
		Integer count = customerDao.deleteCustomer(1, isDelete, modifiedUser, modifiedTime);
		System.err.println(count);
	}
	
	@Test
	public void delete3213() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("createdTime", "2019");
		System.err.println(map);
		List<CustomerTime> count = customerDao.selectYearMonth(map);
		for (CustomerTime customerTime : count) {
			System.err.println(customerTime);
		}
		
	}
}
