package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.TaotaoResult;

public interface TbItemService extends BaseService<TbItem, TbItemExample, Long>{
	TbItem getItemById(Long itemId);
	
	EasyUIDataGridResult getItemResult(int page,int rows);
	
	TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception;
	
}
