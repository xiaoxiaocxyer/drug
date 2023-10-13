package cn.tedu.drug.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.DrugDao;
import cn.tedu.drug.entity.Drug;
import cn.tedu.drug.entity.DrugANDDrugCategory;
import cn.tedu.drug.util.ExcelFromInterface;
import cn.tedu.drug.util.ExcelFromServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelDrugTestCase {
	@Autowired
	private DrugDao drugDao;
	
	@Test
	public void ExcelDrug() {
		String title="药品表";
		String[] rowName = new String[]{"编号","是否删除","价格"};
//		List<Drug> drugs = (List<Drug>) session.getAttribute("drugs");
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		map.put("categoryId", 2);
		List<DrugANDDrugCategory> drugs = drugDao.selectDrug(map);
		for (DrugANDDrugCategory drugANDDrugCategory : drugs) {
			System.err.println(drugANDDrugCategory);
		}
		List<Object[]>  list = new ArrayList<Object[]>();
		String url="D:/t_drug.xlsx";
		Object[] objs = null;
		for (int i = 0; i < drugs.size(); i++) {
			DrugANDDrugCategory drug = drugs.get(i);
			objs = new Object[rowName.length];
			objs[0] = drug.getId();
			if (drug.getIsDelete() ==0) {
				objs[1] = "未删除";
			}else{
				objs[1] = "已删除";
			}
			objs[2] = drug.getPleasedTo();
			list.add(objs);
		}
		System.err.println(Arrays.toString(objs));
		System.err.println(list);
		try {
			ExcelFromInterface excelInterface=new ExcelFromServiceImpl();
			excelInterface.excel(title, rowName, list, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
