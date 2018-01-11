package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample;
import com.taotao.service.TbOrderItemService;

@Service
public class TbOrderItemServiceImpl extends BaseServiceImpl<TbOrderItem, TbOrderItemExample, Long> implements TbOrderItemService {

    @Autowired
    public void setBaseMapper(TbOrderItemMapper mapper) {
        this.baseMapper = mapper;
    }
}
