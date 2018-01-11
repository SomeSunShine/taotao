package com.taotao.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.HttpClientUtil;
import com.taotao.util.PageResult;
import com.taotao.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.TbContentService;

/** 
 * @ClassName: TbContentController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月11日 下午4:54:20  
 */
/** 
 * @ClassName: TbContentController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月11日 下午4:54:25  
 */
@Controller
@RequestMapping("/content")
public class TbContentController {

	@Autowired
	private TbContentService tbContentService;
	
	@Value("${REST_BASE_URL}]")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@RequestMapping(value = "/query/list", method = RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult list(Long categoryId,int page, int rows) {
		TbContentExample example = new TbContentExample();
		if (categoryId!=0) {
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);			
		}
		// pagehaper
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentService.selectByExample(example);
		
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		

		PageInfo<TbContent> pageinfo = new PageInfo(list);
		long total = pageinfo.getTotal();
		result.setTotal(total);

		return result;
	}
	
	
	/** 
	 * 添加内容
	 * @Title: insertTbContent 
	 * @Description: TODO
	 * @param tbContent
	 * @return
	 * @return: TaotaoResult
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertTbContent(TbContent tbContent){
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentService.insert(tbContent);
		
		//添加缓存同步
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+tbContent.getCategoryId());
		} catch (Exception e) {
			//如果同步错误,则可以发短信 或者 发邮件给管理员.
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateTbContent(TbContent tbContent){
		tbContent.setUpdated(new Date());
		tbContentService.updateByPrimaryKey(tbContent);
		return TaotaoResult.ok();
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteTbContent(String ids){
		String[] idArray=ids.split(",");
		for (String idStr : idArray) {
			Long id=Long.parseLong(idStr);
			tbContentService.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

	
}
