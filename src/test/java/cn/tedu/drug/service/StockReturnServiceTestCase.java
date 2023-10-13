package cn.tedu.drug.service;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.StockReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockReturnServiceTestCase {
	
	@Autowired
	IStockReturnService returnService;
	
	@Test
	public void addStockReturn() throws ParseException{
		StockReturn stock = new StockReturn();
		stock.setDrugName("汾酒");
		stock.setStockOrder("331589");
		stock.setReturnOrder("123219");
		stock.setDrugAddress("山炮屯");
		stock.setReturnTime("2019-02-05");
		stock.setEmployeesName("小久");
		stock.setDrugPrice(14.8);
		stock.setNumber(20);
		stock.setReturnPrice(220.0);
		stock.setAmount(4000.0);
		stock.setWhy("一喝汾酒就上头");
		returnService.addStockReturn(stock, "小强");
		System.err.println("OK");
	}
}