package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult=new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	/**
	 * 查询分类列表
	 * @Title: getCatList 
	 * @Description: TODO
	 * @param parentId
	 * @return
	 * @return: List<?>
	 */
	private List<?> getCatList(long parentId){
		//设置参数
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询,获得查询数据
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//处理查询结果
		List resultList=new ArrayList<>();	//存data
		
		//标记(防止页面混乱)
		int count=0;
		//遍历结果集构建要求的json格式
		for (TbItemCat tbItemCat : list) {
			//如果是父节点:
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				//父节点n
				if (parentId==0) {
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");				
				}else{
					//非父节点n
					node.setName(tbItemCat.getName());
				}
				node.setUrl( "/products/"+tbItemCat.getId()+".html");
				//递归的运用
				node.setItem(getCatList(tbItemCat.getId()));	//加载子节点
				resultList.add(node);
				count++;
				
				//第一层只取14条记录
				if (count>=14&&parentId==0) {
					break;
				}
				
				//如果是子节点:
			}else{
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return resultList;
	}
}
