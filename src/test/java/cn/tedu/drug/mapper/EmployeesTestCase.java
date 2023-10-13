package cn.tedu.drug.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.EmployeesDao;
import cn.tedu.drug.entity.Employees;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesTestCase {
	
	@Autowired
	private EmployeesDao employeesDao;
	
	
	@Test
	public void findByUidTest() {
		List<Employees> employees =  employeesDao.findByUidUsername();
		System.err.println(employees);
	}
	
	
	@Test
	public void insertEmployees() {
		List<Employees> employees =  employeesDao.findByUidUsername();
		System.err.println(employees);
	}
	
	@Test
	public void ploadInfo() {
		Employees emp = new Employees();
		emp.setUid(36);
		emp.setAvatar("123.jpg");
		emp.setUsername("qqqqqqq");
		emp.setPhone("213231");
		emp.setPassword("123321");
		emp.setEmail("2131313");
		Integer row = employeesDao.uploadInfo(emp);
		System.out.println(row);
		
	}
}
