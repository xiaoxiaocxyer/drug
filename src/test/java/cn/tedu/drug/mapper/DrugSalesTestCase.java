package cn.tedu.drug.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.DrugSalesDao;
import cn.tedu.drug.dao.LogOperationDao;
import cn.tedu.drug.entity.DrugSales;
import cn.tedu.drug.entity.DrugSalesANDCustomer;
import cn.tedu.drug.entity.LogOperation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugSalesTestCase {
	
	@Autowired
	private DrugSalesDao drugSalesDao;
	
	@Test
	public void testInsert(){
		DrugSales drugSales = new DrugSales();
		drugSales.setDocumentNo("123456");
		drugSales.setInventoryQuantity(2);
		drugSales.setPrice(2.2);
		drugSales.setInventory(5.5);
		drugSales.setStorageTime(new Date());
		drugSales.setDrugName("按摩系");
		drugSales.setCustomerId(12);
		Integer rows = drugSalesDao.insertDrugSales(drugSales);
		System.err.println(rows);
	}
	
	@Test
	public void testInsert312(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		List<DrugSalesANDCustomer> count = drugSalesDao.getselectDrugSalesPage(map);
		System.err.println(count);
	}
	
	@Test
	public void testInsert3ds12(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		Long count = drugSalesDao.getselectDrugSalesCount(map);
		System.err.println(count);
	}
	
}














