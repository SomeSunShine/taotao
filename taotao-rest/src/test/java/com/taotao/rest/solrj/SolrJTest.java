package com.taotao.rest.solrj;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	/** 
	 * 添加/修改文档
	 * @Title: addDocument 
	 * @Description: TODO
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void addDocument() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.182.210:8080/solr");
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", "test1");
		document.addField("item_title", "测试1");
		
		solrServer.add(document);
		solrServer.commit();
	}
	
	/** 
	 * 删除索引文档
	 * @Title: deleteDocument 
	 * @Description: TODO
	 * @throws Exception
	 * @return: void
	 */
	public void deleteDocument() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.182.210:8080/solr");
		solrServer.deleteById("testId");	//通过id删除
//		solrServer.deleteById("list");		//通过list,批量删除
		solrServer.deleteByQuery("*:*");	//查询删除所有		
	}
	
	
	
}
