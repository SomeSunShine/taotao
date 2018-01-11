package com.taotao.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.jdi.VoidTypeImpl;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.TbItemCatService;
import com.taotao.util.EasyUITreeNode;

@Service
public class TbItemCatServiceImpl extends BaseServiceImpl<TbItemCat, TbItemCatExample, Long> implements TbItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
    @Autowired
    public void setBaseMapper(TbItemCatMapper mapper) {
        this.baseMapper = mapper;
    }
    
    
	/*  
	 * 商品分类注解
	 * @Title: getItemCatList
	 * @Description: TODO
	 * @param parentId
	 * @return 
	 * @see com.taotao.service.TbItemCatService#getItemCatList(long) 
	 */
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		TbItemCatExample example=new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询结果
		List<TbItemCat> list=tbItemCatMapper.selectByExample(example);
		//转换成树形列表
		List<EasyUITreeNode> resultList=new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表中
			resultList.add(node);
			
		}
		return resultList;
	}
    
    
}
