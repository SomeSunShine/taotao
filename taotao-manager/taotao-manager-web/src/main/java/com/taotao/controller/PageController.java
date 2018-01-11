package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: PageController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月2日 下午2:21:03  
 */
@Controller
public class PageController {
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	
	
	/**
	 * 页面展示. 
	 * 因为请求的url与jsp的名称一致;故可以进行统一管理. 提取url并返回作为.jsp的名称即可.
	 * 
	 * @Title: showPage 
	 * @Description: TODO
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
}






