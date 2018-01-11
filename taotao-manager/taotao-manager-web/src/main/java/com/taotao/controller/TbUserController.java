package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.service.TbUserService;

@Controller
@RequestMapping("/tbUser")
public class TbUserController {

	@Autowired
	private TbUserService tbUserService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public PageResult list(int page, int rows) {
		TbUserExample example = new TbUserExample();
		// pagehaper
		PageHelper.startPage(page, rows);
		List<TbUser> list = tbUserService.selectByExample(example);

		PageResult pageResult = new PageResult();
		pageResult.setRows(list);

		PageInfo<TbUser> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();

		pageResult.setTotal(total);

		return pageResult;
	}

}
