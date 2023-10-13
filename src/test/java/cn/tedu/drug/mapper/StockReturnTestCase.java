package cn.tedu.drug.mapper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.tedu.drug.dao.StockReturnDao;
import cn.tedu.drug.entity.StockReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockReturnTestCase {
	
	@Autowired
	StockReturnDao stockReturn;
	
	@Test
	public void insertStockReturn() throws ParseException{
		StockReturn stock = new StockReturn();
		stock.setDrugName("摇头丸");
		stock.setStockOrder("33161658");
		stock.setReturnOrder("123456789");
		stock.setDrugAddress("国际庄");
		stock.setReturnTime("2018-01-29");
		stock.setEmployeesName("小明");
		stock.setDrugPrice(12.8);
		stock.setNumber(10);
		stock.setReturnPrice(20.0);
		stock.setAmount(200.0);
		stock.setWhy("吃了摇头丸不摇头");
		stock.setCreatedUser("小黄");
		stock.setCreatedTime(new Date());
		stockReturn.insertStockReturn(stock);
		System.err.println("OK");
	}
	
	@Test
	public void findStockReturn(){
		List<StockReturn> list = stockReturn.findStockReturn(1,2,"");
		System.err.println("查询到:"+list.size());
		for(StockReturn s : list){
			System.err.println(s);
		}
	}
	
}