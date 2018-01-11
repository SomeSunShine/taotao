package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.util.PageResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderExample;
import com.taotao.service.TbOrderService;



@Controller
@RequestMapping("/tbOrder")
public class TbOrderController  {

    @Autowired
    private TbOrderService tbOrderService;



    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult list(int page,int rows) {
        TbOrderExample example=new TbOrderExample();
        //pagehaper
        PageHelper.startPage(page, rows);
        List<TbOrder> list=tbOrderService.selectByExample(example);

            PageResult pageResult=new PageResult();
            pageResult.setRows(list);

            PageInfo<TbOrder> pageinfo=new PageInfo<>(list);
                long total=pageinfo.getTotal();

                pageResult.setTotal(total);


                return pageResult;
    }



}
