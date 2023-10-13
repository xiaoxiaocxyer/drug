package cn.tedu.drug.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.DrugStock;
import cn.tedu.drug.entity.DrugStockFindAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugStockServiceTestCase {
	
	@Autowired
	private IDrugStockService drugStockService;
	
	@Test
	public void findEmpSupId(){
		List<List<Map<Integer, String>>> id = drugStockService.findDrgEmpSupId();
		System.err.println(id.size());
		for(List<Map<Integer, String>> i : id){
			System.err.println("*********************");
			for(Map<Integer, String> m : i){
				Set<Entry<Integer,String>> set = m.entrySet();
				for(Entry<Integer,String> e : set){
					System.err.println("key值："+e.getKey()+" value值："+e.getValue());
				}
			}
		}
	}
	
	@Test
	public void findDrugStock(){
		Integer pageNoStr = 0;
		Integer pageSizeStr = 10;
		String documentNo = "";
		List<DrugStockFindAll> list = drugStockService.findDrugStock(pageNoStr,pageSizeStr,documentNo);
		for(DrugStockFindAll d : list){
			System.err.println(d);
		}
	}
	
	@Test
	public void deleteDrugStock(){
		
	}
}
