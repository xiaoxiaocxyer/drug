package cn.tedu.drug.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.DrugDao;
import cn.tedu.drug.entity.Drug;
import cn.tedu.drug.entity.DrugANDDrugCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugTestCase {
	
	@Autowired
	private DrugDao drugDao;
	
	@Test
	public void findUidNamfdse(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("drugName", "");
		map.put("unit", "");
		map.put("origin", "");
		map.put("categoryId", "");
		List<DrugANDDrugCategory> drug = drugDao.findselectIsdelete(map);
		for(DrugANDDrugCategory d : drug){
			System.err.println(d.getId()+" :"+d.getDrugName());
		}
	}
	
	@Test
	public void findUidName321(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		long drug = drugDao.selectCountDrug(map);
		System.err.println(drug);
	}
	
	@Test
	public void findUidName321321(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		map.put("categoryId", 2);
		List<DrugANDDrugCategory> drug = drugDao.selectDrug(map);
		for (DrugANDDrugCategory drugANDDrugCategory : drug) {
			System.err.println(drugANDDrugCategory);
		}
		
	}
	
}