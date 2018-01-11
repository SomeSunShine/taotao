package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.TaotaoResult;

/** 
 * 商品查询controller
 * @ClassName: SearchController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月16日 下午11:56:19  
 */
@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q") String queryString,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="60")Integer rows){
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		try {
			queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
			SearchResult searchResult=null;
			searchResult = searchService.search(queryString, page, rows);
			return TaotaoResult.ok(searchResult);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
