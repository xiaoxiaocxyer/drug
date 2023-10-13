package cn.tedu.drug.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.SupplierDao;
import cn.tedu.drug.entity.Supplier;
import cn.tedu.drug.service.exception.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierTestCase {
	
	@Autowired
	private SupplierDao supplierDao;
	
	
	@Test
	public void findByUidTest() {
		List<Supplier> supplier =  supplierDao.findByUidUsername();
		System.err.println(supplier);
	}
	
	@Test
	public void testInsert(){
		Supplier supplier = new Supplier();
		supplier.setUsername("黑龙江药厂");
		supplier.setPhone("1500000000");
		supplier.setEmail("158@autozi.com");
		supplier.setAddress("黑龙江绥化");
		Integer rows = supplierDao.insert(supplier);
		System.err.println(rows);
	}
	
	@Test
	public void testFindByUid(){
		try {
			Integer uid = 3;
			Supplier supplier = supplierDao.findByUid(uid);
			System.out.println(supplier);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testUpdateInfo(){
		Supplier supplier = new Supplier();
		supplier.setUid(8);
		supplier.setPhone("1855222");
		supplier.setEmail("66666@autozi.com");
		supplier.setAddress("河北省行唐县");
		supplier.setCardBank("中国银行");
		supplier.setCard("8888888888888");
		supplierDao.updateInfo(supplier);
		System.err.println("OK");
	}
	
	@Test
	public void testSelectAll(){
		List<Supplier> suppliers = supplierDao.findAll();
		System.err.println(suppliers);
	}
	@Test
	public void testUpdateIsDelete(){
		Integer uid = 2;
		String modifiedUser = "超级管理员";
		Date modifiedTime = new Date();
		supplierDao.updateIsDelete(uid, modifiedUser, modifiedTime);
		System.err.println("OK");
	}
}










