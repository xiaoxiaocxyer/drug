package cn.tedu.drug.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.entity.DrugANDDrugCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugTestCase {
	
	@Autowired
	private IDrugService drugService;
	
	@Test
	public void findUidNamfdse(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("drugName", "");
		map.put("unit", "");
		map.put("origin", "");
		map.put("categoryId", "1");
		List<DrugANDDrugCategory> drug = drugService.findselectIsdelete(map);
		for(DrugANDDrugCategory d : drug){
			System.err.println(d.getId()+" :"+d.getDrugName());
		}
	}
	
	
}
