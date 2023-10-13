package cn.tedu.drug.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.DrugCategoryDao;
import cn.tedu.drug.entity.DrugCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugCategoryTestCase {
	
	@Autowired
	private DrugCategoryDao drugCategoryDao;
	
	
	@Test
	public void findByUidTest() {
		List<DrugCategory> drugCategory =  drugCategoryDao.findByCategoryIdCategoryName();
		System.err.println(drugCategory);
	}
	
	@Test
	public void findByTest() {
		DrugCategory drugCategory =  drugCategoryDao.findByCategoryId(2);
		System.err.println(drugCategory);
	}
	
	@Test
	public void findByTest123() {
		Map<String ,Object> map = new HashMap<String ,Object>();
		long drugCategory =  drugCategoryDao.selectCountDrugCategory(map);
		System.err.println(drugCategory);
	}
	
	@Test
	public void findByTest345() {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 1);
		map.put("CategoryName", null);
		map.put("pageSize", 10);
		List<DrugCategory> drugCategory =  drugCategoryDao.selectDrugCategoryForPage(map);
		System.err.println(drugCategory);
	}
	
	@Test
	public void findByTest345321() {
		String[] categoryIds = {"15","16"};
//		int[] categoryIds = {17,18};
		Integer count = drugCategoryDao.deleteDrugCategoryByids(categoryIds);
		System.out.println(count);
	}
	
	@Test
	public void findByTest345321432() {
		String str = "";
		for (int i = 0; i < 5; i++) {
			str += "123";
		}
		System.out.println(str.trim().length());
	}
	
	
}
