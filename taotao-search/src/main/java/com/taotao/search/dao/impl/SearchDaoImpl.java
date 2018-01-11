package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.pojo.SearchResult;

/** 
 * 查询结果返回
 * @ClassName: SearchDaoImpl 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月17日 下午3:50:49  
 */
@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult search(SolrQuery query) throws Exception{
		//返回值对象
		SearchResult result=new SearchResult();
		//执行查询
		QueryResponse response=solrServer.query(query);
		//取回查询结果
		SolrDocumentList results = response.getResults();
		//查询结果总数量
		result.setRecordCount(results.getNumFound());
		//商品列表
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument item : results) {
			//创建商品对象
			SearchItem searchItem=new SearchItem();
			
			searchItem.setId(item.get("id")+"");
			searchItem.setImage((String) item.get("item_image"));
			searchItem.setPrice((long) item.get("item_price"));
			searchItem.setSell_point((String) item.get("item_sell_point"));
			searchItem.setCategory_name((String) item.get("item_category_name"));
			//取高亮
			Map<String, Map<String, List<String>>> highlighting=response.getHighlighting();
			List<String> list = highlighting.get(item.get("id")).get("item_title");
			String itemTitle="";
			if (list!=null&&list.size()>0) {	//这里做判断是因为如果没有高亮,则可能导致异常.故加以判断,防止返回空值异常.
				//取高亮结果
				itemTitle=list.get(0);
			}else {
				itemTitle=(String)item.get("item_title");
			}
			searchItem.setTitle(itemTitle);
			
			//添加到商品列表
			itemList.add(searchItem);
		}
		result.setItemList(itemList);
		return result;
	}
}
