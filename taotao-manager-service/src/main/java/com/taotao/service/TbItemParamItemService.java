package com.taotao.service;


import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;

public interface TbItemParamItemService extends BaseService<TbItemParamItem, TbItemParamItemExample, Long>{
	String getItemParamItemByItemId(Long itemId);
}
