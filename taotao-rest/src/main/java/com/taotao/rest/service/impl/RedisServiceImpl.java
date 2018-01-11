package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.TaotaoResult;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public TaotaoResult syncContent(long contentId) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentId+"");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "key删除异常!", ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
