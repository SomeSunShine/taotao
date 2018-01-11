package com.taotao.rest.service;

import com.taotao.util.TaotaoResult;

public interface RedisService {
	TaotaoResult syncContent(long contentId);
}
