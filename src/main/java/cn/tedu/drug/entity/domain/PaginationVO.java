package cn.tedu.drug.entity.domain;

import java.util.List;
/**
 * 分页实体类
 * @author PHP
 *
 * @param <T>
 */
public class PaginationVO<T> {
	private List<T> dataList;
	private long count;
	
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "PaginationVO [dataList=" + dataList + ", count=" + count + "]";
	}
	
	
}
