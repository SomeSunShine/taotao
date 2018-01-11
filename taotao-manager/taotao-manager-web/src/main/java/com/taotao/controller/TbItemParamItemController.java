package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.TbItemParamItemService;

@Controller
@RequestMapping("/item/param/item")
public class TbItemParamItemController {

	@Autowired
	private TbItemParamItemService tbItemParamItemService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public PageResult list(int page, int rows) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		// pagehaper
		PageHelper.startPage(page, rows);
		List<TbItemParamItem> list = tbItemParamItemService.selectByExample(example);

		PageResult pageResult = new PageResult();
		pageResult.setRows(list);

		PageInfo<TbItemParamItem> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();

		pageResult.setTotal(total);

		return pageResult;
	}
	
	@RequestMapping("/show/{itemId}")
	public String showItemParamItem(@PathVariable Long itemId,Model model){
		String string=tbItemParamItemService.getItemParamItemByItemId(itemId);
		model.addAttribute("itemParam",string);
		return "item";
	}
	
	
}
