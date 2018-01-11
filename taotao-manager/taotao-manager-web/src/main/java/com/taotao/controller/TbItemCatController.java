package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.EasyUITreeNode;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.TbItemCatService;



@Controller
@RequestMapping("/item/cat")
public class TbItemCatController  {

    @Autowired
    private TbItemCatService tbItemCatService;

    
    @RequestMapping(value = "listExample", method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(int page,int rows) {
        TbItemCatExample example=new TbItemCatExample();
        //pagehaper
        PageHelper.startPage(page, rows);
        List<TbItemCat> list=tbItemCatService.selectByExample(example);

            PageResult pageResult=new PageResult();
            pageResult.setRows(list);

            PageInfo<TbItemCat> pageinfo=new PageInfo<>(list);
                long total=pageinfo.getTotal();

                pageResult.setTotal(total);


                return pageResult;
    }
    
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
    	List<EasyUITreeNode> list = tbItemCatService.getItemCatList(parentId);
    	return list;
    }    
}
