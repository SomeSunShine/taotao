package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.SearchItem;
import com.taotao.search.service.ItemService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importAllItems(){
		//查询数据
		List<SearchItem> list = itemMapper.getItemList();
		try {
			//数据加入到索引库
			for (SearchItem searchItem : list) {
				SolrInputDocument document=new SolrInputDocument();
				document.setField("id", searchItem.getId());
				document.setField("item_title", searchItem.getTitle());
				document.setField("item_sell_point", searchItem.getSell_point());
				document.setField("item_price", searchItem.getPrice());
				document.setField("item_image", searchItem.getImage());
				document.setField("item_category_name", searchItem.getCategory_name());
				document.setField("item_desc", searchItem.getItem_desc());
				solrServer.add(document);
			}
			//提交修改
			solrServer.commit();					
		} catch (Exception e) {
			e.printStackTrace();
			//出现异常的处理
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}
}
