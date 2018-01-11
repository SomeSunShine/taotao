package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.PageResult;
import com.taotao.util.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.TbItemParamService;



@Controller
@RequestMapping("/item/param")
public class TbItemParamController  {

    @Autowired
    private TbItemParamService tbItemParamService;



    @RequestMapping(value = "listExample", method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(int page,int rows) {
        TbItemParamExample example=new TbItemParamExample();
        //pagehaper
        PageHelper.startPage(page, rows);
        List<TbItemParam> list=tbItemParamService.selectByExample(example);

            PageResult pageResult=new PageResult();
            pageResult.setRows(list);

            PageInfo<TbItemParam> pageinfo=new PageInfo<>(list);
                long total=pageinfo.getTotal();

                pageResult.setTotal(total);


                return pageResult;
    }

    
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult itemParamList(Integer page,Integer rows){
    	EasyUIDataGridResult result=new EasyUIDataGridResult();
    	result=tbItemParamService.getItemParam(page, rows);
    	return result;
    }
    
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
    	TaotaoResult result=tbItemParamService.getItemParamByCid(itemCatId);
    	return result;
    }
    
    @RequestMapping(value="/save/{cid}",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
    	TbItemParam itemParam=new TbItemParam();
    	itemParam.setItemCatId(cid);
    	itemParam.setParamData(paramData);
    	TaotaoResult result = tbItemParamService.insertItemParam(itemParam);
    	return result;
    }

}
