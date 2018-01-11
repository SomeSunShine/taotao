package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.PageResult;
import com.taotao.util.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.TbItemService;

@Controller
@RequestMapping("/item")
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	
	
	/** 
	 * 举个例子,无需理会
	 * @Title: list 
	 * @Description: TODO
	 * @param page
	 * @param rows
	 * @return
	 * @return: EasyUIDataGridResult
	 */
	@RequestMapping(value = "/listEaxmple", method = RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult listEaxmple(Integer page, Integer rows) {
		TbItemExample example = new TbItemExample();
		// pagehaper
		PageHelper.startPage(page, rows);	//给紧邻的分页
		List<TbItem> list = tbItemService.selectByExample(example);

		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		PageInfo<TbItem> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();

		result.setTotal(total);

		return result;
	}
	
	
	
	/** 
	 * 商品信息list
	 * 
	 * @Title: getItemList 
	 * @Description: TODO
	 * @param page
	 * @param rows
	 * @return
	 * @return: EasyUIDataGridResult
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result = tbItemService.getItemResult(page, rows);
		return result;
		
	}
	
	
	/** 
	 * 通过itemId查询商品信息
	 * 
	 * @Title: geTbItemById 
	 * @Description: TODO
	 * @param iteamId
	 * @return
	 * @return: TbItem
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	private TbItem geTbItemById(@PathVariable Long itemId){	//@PathVariable与@RequestParam区别 -- ?前后参数
		TbItem item=tbItemService.getItemById(itemId);
		return item;
	}
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
		TaotaoResult result=tbItemService.createItem(item,desc,itemParams);
		return result;
	}
	
}
