package com.taotao.util;

import java.util.List;

/** 
 * json 分页数据包装
 * @ClassName: EasyUIDataGridResult 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月2日 下午8:01:01  
 */
public class EasyUIDataGridResult {
	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
