package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingExample;
import com.taotao.service.TbOrderShippingService;

@Controller
@RequestMapping("/tbOrderShipping")
public class TbOrderShippingController {

	@Autowired
	private TbOrderShippingService tbOrderShippingService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public PageResult list(int page, int rows) {
		TbOrderShippingExample example = new TbOrderShippingExample();
		// pagehaper
		PageHelper.startPage(page, rows);
		List<TbOrderShipping> list = tbOrderShippingService.selectByExample(example);

		PageResult pageResult = new PageResult();
		pageResult.setRows(list);

		PageInfo<TbOrderShipping> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();

		pageResult.setTotal(total);

		return pageResult;
	}

}
