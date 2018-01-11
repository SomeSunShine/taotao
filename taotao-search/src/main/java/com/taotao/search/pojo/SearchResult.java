package com.taotao.search.pojo;

import java.util.List;

/**
 * solr查询结果
 *  
 * @ClassName: SearchResult 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月17日 下午2:39:37  
 */
public class SearchResult {
	// 商品列表
	private List<SearchItem> itemList;
	// 总记录数
	private long recordCount;
	// 总页数
	private long pageCount;
	// 当前页
	private long curPage;

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

}
