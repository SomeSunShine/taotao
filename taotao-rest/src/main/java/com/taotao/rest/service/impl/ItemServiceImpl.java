package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import com.taotao.util.JsonUtils;
import com.taotao.util.TaotaoResult;

/** 
 * 商品信息
 * 
 * @ClassName: ItemServiceImpl 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月18日 上午9:41:27  
 */
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	/* 商品基本信息
	 * @Title: getItemBaseInfo
	 * @Description: TODO
	 * @param itemId
	 * @return 
	 * @see com.taotao.rest.service.ItemService#getItemBaseInfo(java.lang.Long) 
	 */
	@Override
	public TaotaoResult getItemBaseInfo(Long itemId) {
		//获取缓存
		try {
			String json= jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":base");
			if (!StringUtils.isBlank(json)) {
				TbItem item=JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		
		try {
			//添加缓存
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base", JsonUtils.objectToJson(item));
			//设置有效期
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok(item);
	}

	/* 商品详情
	 * @Title: getItemDesc
	 * @Description: TODO
	 * @param itemId
	 * @return 
	 * @see com.taotao.rest.service.ItemService#getItemDesc(java.lang.Long) 
	 */
	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		//获取缓存
		try {
			
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":desc");
			if (!StringUtils.isBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(itemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			//添加缓存
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc",JsonUtils.objectToJson(itemDesc));
			//设置有效时间
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc", REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok(itemDesc);	
	}

	/* 商品规格
	 * @Title: getItemParam
	 * @Description: TODO
	 * @param itemId
	 * @return 
	 * @see com.taotao.rest.service.ItemService#getItemParam(java.lang.Long) 
	 */
	@Override
	public TaotaoResult getItemParam(Long itemId) {
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":param");
			if (!StringUtils.isBlank(json)) {
				TbItemParamItem itemParamItem=JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return TaotaoResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example=new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if (list!=null&&list.size()>0) {
			TbItemParamItem itemParamItem=list.get(0);
			try {
				jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":param", JsonUtils.objectToJson(itemParamItem));
				jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":param", REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return TaotaoResult.ok(list);
		}
		return TaotaoResult.build(400, "无此商品规格");
	}
	
}

