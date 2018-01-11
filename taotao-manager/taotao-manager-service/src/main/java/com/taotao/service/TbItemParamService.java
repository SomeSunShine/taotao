package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.TaotaoResult;

public interface TbItemParamService extends BaseService<TbItemParam, TbItemParamExample, Long>{
	EasyUIDataGridResult getItemParam(Integer page,Integer rows);
	
	TaotaoResult getItemParamByCid(Long cid);
	
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
