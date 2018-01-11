package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.service.ItemService;
import com.taotao.util.TaotaoResult;

@Controller
@RequestMapping("/manager")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/** 
	 * 导入数据到索引库
	 * @Title: importAllItems 
	 * @Description: TODO
	 * @return
	 * @return: TaotaoResult
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItems(){
		TaotaoResult result =itemService.importAllItems();
		return result;
	}
}
