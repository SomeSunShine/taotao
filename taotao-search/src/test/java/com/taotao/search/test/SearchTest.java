package com.taotao.search.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SearchTest {
	@Test
	public void queryDocument() throws Exception {
		//solr客户端,单机版
		SolrServer solrServer=new HttpSolrServer("http://192.168.182.210:8080/solr");
		//创建一个查询对象
		SolrQuery query=new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		
		//执行查询
		QueryResponse response=solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList=response.getResults();
		System.out.println("共查询记录数: "+solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
		}
	}
}
