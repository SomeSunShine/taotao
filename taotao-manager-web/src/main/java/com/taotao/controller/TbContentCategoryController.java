package com.taotao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.taotao.service.TbContentCategoryService;
import com.taotao.util.EasyUITreeNode;
import com.taotao.util.PageResult;
import com.taotao.util.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController {

	@Autowired
	private TbContentCategoryService tbContentCategoryService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> list(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// pagehaper
		// PageHelper.startPage(page, rows);
		List<TbContentCategory> list = tbContentCategoryService.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			node.setText(tbContentCategory.getName());
			resultList.add(node);
		}
		/*
		 * PageResult pageResult = new PageResult(); pageResult.setRows(list);
		 * 
		 * PageInfo<TbContentCategory> pageinfo = new PageInfo<>(list); long
		 * total = pageinfo.getTotal();
		 * 
		 * pageResult.setTotal(total);
		 */

		return resultList;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult insertContentCategory(Long parentId, String name) {
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setId(parentId);
		contentCategory.setIsParent(false);
		;
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setUpdated(new Date());
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		tbContentCategoryService.insert(contentCategory);

		TbContentCategory parentCat = tbContentCategoryService.selectByPrimaryKey(parentId);
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			tbContentCategoryService.updateByPrimaryKey(parentCat);
		}

		return TaotaoResult.ok();
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id) {
		// 查询当前节点信息
		TbContentCategory tbContentCategory = tbContentCategoryService.selectByPrimaryKey(id);
		System.out.println(tbContentCategory.getIsParent());
		
		Long parentId = tbContentCategory.getId();
		
		// 当前节点为父节点吗
		if (tbContentCategory.getIsParent()) {
			// 返回子节点作为集合
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> nodes = tbContentCategoryService.selectByExample(example);

			// 如果父节点的子节点少于1,则删除父节点
			System.out.println(nodes.size());
			if (nodes.size() < 1) {
				tbContentCategory.setIsParent(false);
				tbContentCategory.setUpdated(new Date());
				tbContentCategoryService.updateByPrimaryKey(tbContentCategory);
			}else{
				//递归删除父节点下的子节点
				for (TbContentCategory node : nodes) {
					deleteContentCategory(node.getId());
				}
				tbContentCategoryService.deleteByPrimaryKey(parentId);
			}
			
		} else {
			// 删除当前节点
			parentId=tbContentCategory.getParentId();
			tbContentCategoryService.deleteByPrimaryKey(id);
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> nodes = tbContentCategoryService.selectByExample(example);

			// 如果父节点的子节点少于1,则删除父节点
			System.out.println(nodes.size());
			if (nodes.size() < 1) {
				tbContentCategory.setIsParent(false);
				tbContentCategory.setUpdated(new Date());
				tbContentCategoryService.updateByPrimaryKey(tbContentCategory);
			}
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult renameContentCategory(Long id,String name) {
		// 查询当前节点信息
		TbContentCategory tbContentCategory = tbContentCategoryService.selectByPrimaryKey(id);
		tbContentCategory.setName(name);
		tbContentCategoryService.updateByPrimaryKey(tbContentCategory);
		return TaotaoResult.ok();
	}

}
