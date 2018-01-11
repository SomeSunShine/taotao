package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.rest.service.RedisService;
import com.taotao.util.TaotaoResult;

/**
 * 缓存同步 
 * @ClassName: RedisController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月14日 下午9:54:14  
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentId}")
	public TaotaoResult contentCacheSync(@PathVariable Long contentId){
		TaotaoResult result=redisService.syncContent(contentId);
		return result;
	}
}
