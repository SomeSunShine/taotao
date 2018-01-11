package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/search")
	public String name(@RequestParam(value="q",defaultValue="手机")String queryString,@RequestParam(value="page",defaultValue="1")Integer page,Model model) {
		try {
			if (queryString!=null) {
				queryString=new String(queryString.getBytes("iso8859-1"), "utf-8");
			}
			SearchResult searchResult = searchService.search(queryString, page);
			model.addAttribute("query",queryString);
			model.addAttribute("totalPages", searchResult.getPageCount());
			model.addAttribute("itemList", searchResult.getItemList());
			model.addAttribute("page",page);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		return "search";
	}
}
