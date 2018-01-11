package com.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.IDUtils;
import com.taotao.util.TaotaoResult;

/** 
 * 商品查询
 * 
 * @ClassName: TbItemServiceImpl 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月1日 下午7:35:01  
 */
@Service
public class TbItemServiceImpl extends BaseServiceImpl<TbItem, TbItemExample, Long> implements TbItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
    @Autowired
    public void setBaseMapper(TbItemMapper mapper) {
        this.baseMapper = mapper;
    }

	@Override
	public TbItem getItemById(Long itemId) {
//		TbItem item=tbItemMapper.selectByPrimaryKey(itemId);
		TbItemExample example=new TbItemExample();
		Criteria createCriteria = example.createCriteria();	//创建查询条件
		createCriteria.andIdEqualTo(itemId);	//添加查询条件
		List<TbItem> list=tbItemMapper.selectByExample(example);
		TbItem item=null;
		if (list!=null&&list.size()>0) {
			item=list.get(0);
		}
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemResult(int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	
	
	@Override
	public TaotaoResult createItem(TbItem item,String desc,String itemParam) throws Exception{
		//生成商品ID
		Long itemId=IDUtils.genItemId();
		item.setId(itemId);
		//设置商品的状态	1正常
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		//插入到数据库
		tbItemMapper.insert(item);
		
		//添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus()!=200) {
			throw new Exception("添加商品描述异常!");	//在这里抛了异常,spring事务将自动回滚. 故不要try...chtch...
		}
		
		//添加规格参数
		result=insertItemParamItem(itemId, itemParam);
		if (result.getStatus()!=200) {
			throw new Exception("商品规格参数添加异常!");
		}
		return TaotaoResult.ok();
	}
	
	//添加规格参数
	private TaotaoResult insertItemParamItem(Long itemId,String itemParam){
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setCreated(new Date());
		itemParamItem.setParamData(itemParam);
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		tbItemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}
    
	//添加商品描述的方法
	private TaotaoResult insertItemDesc(Long itemId,String desc){
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
}
