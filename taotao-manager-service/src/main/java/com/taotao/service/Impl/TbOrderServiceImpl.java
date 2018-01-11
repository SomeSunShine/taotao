package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbOrderMapper;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderExample;
import com.taotao.service.TbOrderService;

@Service
public class TbOrderServiceImpl extends BaseServiceImpl<TbOrder, TbOrderExample, Long> implements TbOrderService {

    @Autowired
    public void setBaseMapper(TbOrderMapper mapper) {
        this.baseMapper = mapper;
    }
}
