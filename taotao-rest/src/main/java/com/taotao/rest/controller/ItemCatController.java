package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.util.JsonUtils;

import redis.clients.jedis.Jedis;


/**
 * 商品分类列表 
 * @ClassName: ItemCatController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月10日 下午5:22:52  
 */
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_ITEMCAT_REDIS_KEY}")
	private String INDEX_ITEMCAT_REDIS_KEY;
	
	/*
	//方法一
	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback){
		CatResult catResult=itemCatService.getItemCatList();
		//把pojo转成字符串
		String json = JsonUtils.objectToJson(catResult);
		//拼装返回值
		String result=callback+"("+json+");";
		return result;
	}*/
	
	//方法二
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback){	//object返回会被自动转换为json
//		jedisClient.del(INDEX_ITEMCAT_REDIS_KEY);
		try {
			String result=jedisClient.get(INDEX_ITEMCAT_REDIS_KEY);
			if (!StringUtils.isBlank(result)) {
				CatResult catResult=JsonUtils.jsonToPojo(result, CatResult.class);
				MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);	//将catResult作为构造
				mappingJacksonValue.setJsonpFunction(callback);	//回调,自动添加为前缀
				return mappingJacksonValue;
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
		CatResult catResult=itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);	//将catResult作为构造
		mappingJacksonValue.setJsonpFunction(callback);	//回调,自动添加为前缀
		
		try {
			String cacheString=JsonUtils.objectToJson(catResult);
			jedisClient.set(INDEX_ITEMCAT_REDIS_KEY, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mappingJacksonValue;
	}
	
}
