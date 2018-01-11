package com.taotao.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.taotao.util.HttpClientUtil;

public class HttpTest {
	@Test
	public void testPost(){
		Map<String, String> param= new HashMap<>();
		System.out.println(param);
		String b=HttpClientUtil.doPost("http://192.168.20.170:80/admin/tsAccount", param);
	}
	
	@Test
	public void testGet(){
		String accout=HttpClientUtil.doGet("http://192.168.20.170:80/admin/account/1510802051504");
		System.out.println(accout);
	}
	
	@Test
	public void testPay(){
		Map<String, String> param=new HashMap<>();
		param.put("account_id", "");
	}
}
