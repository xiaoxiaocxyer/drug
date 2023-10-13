package cn.tedu.drug.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.tedu.drug.entity.LogOperation;
import cn.tedu.drug.entity.domain.PaginationVO;
import cn.tedu.drug.service.ILogOperationService;
import cn.tedu.drug.util.JsonDateValueProcessor;
import cn.tedu.drug.util.ResponseResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@RestController
@RequestMapping("/logOperation")
public class LogOperationController extends BaseController{
	@Autowired	//自动装配
	private ILogOperationService logOperationService;
	
	/**
	 * 查询日志数据，多条件查询
	 * @param drugCategory
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/selectLogOperation")
	public ResponseResult<JSONObject> selectDrugCategory
	(String category,String location,String time,String pageNoStr,String pageSizeStr) throws JsonProcessingException {
		//获取参数
		long pageNo = 1;	//如果没有传数据,默认为第一页
		if( pageNoStr != null && pageNoStr.trim().length()>0 ){
			pageNo = Long.parseLong(pageNoStr);
		}
		int pageSize = 1;	//如果没有传数据,默认为10条数据
		if( pageSizeStr != null && pageSizeStr.trim().length()>0 ){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		long beginNo = (pageNo-1)*pageSize;
		
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("beginNo", beginNo);
		map.put("pageSize", pageSize);
		map.put("category", category);
		map.put("location", location);
		map.put("time", time );
		PaginationVO<LogOperation> vo = logOperationService.getSelectLogOperation(map);
		//解决前后端json遍历的时间问题(把vo换成jsonObj)
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jsonObj = JSONObject.fromObject(vo, jsonConfig);
		return new ResponseResult<JSONObject>(SUCCESS,jsonObj);
	}
}
