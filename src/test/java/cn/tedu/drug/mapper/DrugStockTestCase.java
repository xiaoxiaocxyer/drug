package cn.tedu.drug.mapper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.DrugStockDao;
import cn.tedu.drug.entity.DrugStock;
import cn.tedu.drug.entity.DrugStockFindAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugStockTestCase {
	
	@Autowired
	private DrugStockDao drugStockDao;
	
	
	@Test
	public void findByUidTest() throws ParseException {
		DrugStock drugStock = new DrugStock();
		drugStock.setDocumentNo("123456789");
		drugStock.setInventoryQuantity(12);
		drugStock.setPrice(10.5);
		drugStock.setInventory(12.2);
		drugStock.setStorageTime("");
		drugStock.setDrugId(1);
		drugStock.setSupplierId(1);
		drugStock.setEmployeesId(1);
		drugStock.setIsDelete(0);
		drugStock.setCreatedUser("admin");
		drugStock.setCreatedTime(new Date());
		drugStock.setModifiedUser("admin");
		drugStock.setModifiedTime(new Date());
		Integer count =  drugStockDao.insertDrugStock(drugStock);
		System.err.println(count);
	}
	
	@Test
	public void findAll(){
		Integer pageNoStr = 0;
		Integer pageSizeStr = 10;
		String documentNo = "";
		List<DrugStockFindAll> all = drugStockDao.findAll(pageNoStr, pageSizeStr, documentNo);
		for(DrugStockFindAll d : all){
			System.err.println(d);
		}
	}
	
	@Test
	public void updateIsDelete(){
		Integer id = 4;
		Integer upd = drugStockDao.updateIsDelete(id, "大头", new Date());
		System.err.println("受影响的行数:"+upd);
	}
	
	@Test
	public void updateDrugStock() throws ParseException{
		DrugStock drugStock = new DrugStock();
		drugStock.setId(4);
		drugStock.setDocumentNo("456789");
		drugStock.setInventoryQuantity(5);
		drugStock.setPrice(15.3);
		drugStock.setInventory(50.2);
		drugStock.setStorageTime("2015-02-23");
		drugStock.setDrugId(4);
		drugStock.setSupplierId(6);
		drugStock.setEmployeesId(12);
		Integer upd = drugStockDao.updateDrugStock(drugStock);
		System.err.println("受影响的行数:"+upd);
	}
}
