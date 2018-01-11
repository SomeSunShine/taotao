package com.taotao.rest.service;

import com.taotao.util.TaotaoResult;

public interface ItemService {
	TaotaoResult getItemBaseInfo(Long itemId);
	TaotaoResult getItemDesc(Long itemId);
	TaotaoResult getItemParam(Long itemId);	
}
