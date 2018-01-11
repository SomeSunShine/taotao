package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample;
import com.taotao.service.TbOrderItemService;



@Controller
@RequestMapping("/tbOrderItem")
public class TbOrderItemController  {

    @Autowired
    private TbOrderItemService tbOrderItemService;



    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(int page,int rows) {
        TbOrderItemExample example=new TbOrderItemExample();
        //pagehaper
        PageHelper.startPage(page, rows);
        List<TbOrderItem> list=tbOrderItemService.selectByExample(example);

            PageResult pageResult=new PageResult();
            pageResult.setRows(list);

            PageInfo<TbOrderItem> pageinfo=new PageInfo<>(list);
                long total=pageinfo.getTotal();

                pageResult.setTotal(total);


                return pageResult;
    }



}
