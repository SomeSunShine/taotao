package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.service.TbItemDescService;

@Controller
@RequestMapping("/tbItemDesc")
public class TbItemDescController {

	@Autowired
	private TbItemDescService tbItemDescService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public PageResult list(int page, int rows) {
		TbItemDescExample example = new TbItemDescExample();
		// pagehaper
		PageHelper.startPage(page, rows);
		List<TbItemDesc> list = tbItemDescService.selectByExample(example);

		PageResult pageResult = new PageResult();
		pageResult.setRows(list);

		PageInfo<TbItemDesc> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();

		pageResult.setTotal(total);

		return pageResult;
	}

}
