package cn.tedu.drug.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.drug.dao.LogOperationDao;
import cn.tedu.drug.entity.LogOperation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogOperationTestCase {
	
	@Autowired
	private LogOperationDao operationDao;
	
	
	@Test
	public void testInsert321(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		Long count = operationDao.selectLogOperationCount(map);
		System.err.println(count);
	}
	
	@Test
	public void testInsert32fds1(){
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", 0);
		map.put("pageSize", 10);
		List<LogOperation> count = operationDao.selectLogOperationForPage(map);
		System.err.println(count);
	}
	
	
	
	
	
	public void number(){
		StringBuilder strB = new StringBuilder();
		Random ran = new Random();
		for (int i = 0; i < 8; i++) {
			strB.append(  ran.nextInt(10)  );
		}
		System.out.println(strB.toString());
	};
}














