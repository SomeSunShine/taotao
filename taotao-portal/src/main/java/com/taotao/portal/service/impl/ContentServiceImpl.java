package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import com.taotao.util.HttpClientUtil;
import com.taotao.util.JsonUtils;
import com.taotao.util.TaotaoResult;

/**
 *  调用服务层服务
 * @ClassName: ContentServiceImpl 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月13日 上午12:00:52  
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public String getContentList() {
		//调用服务层的方法
		String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		try {
			TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
			List<TbContent> list=(List<TbContent>) taotaoResult.getData();
			
			//创建一个jsp要求的pojo列表
			List<Map> resultList=new ArrayList<Map>();
			for (TbContent tbContent : list) {
				Map map=new HashMap();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
