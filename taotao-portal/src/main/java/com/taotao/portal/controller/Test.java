package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.util.HttpClientUtil;

@Controller
@ResponseBody
public final class Test {
	@RequestMapping(value="/pachong")
	public String test() {
		String json1=HttpClientUtil.doGet("http://www.en8848.com.cn/article/psycology/self-improvement/71454.html");
		try {
			String jsonString=new String(json1.getBytes("iso8859-1"),"utf-8");
			return jsonString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
