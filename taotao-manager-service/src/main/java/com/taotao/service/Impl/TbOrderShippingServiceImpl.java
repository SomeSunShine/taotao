package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingExample;
import com.taotao.service.TbOrderShippingService;

@Service
public class TbOrderShippingServiceImpl extends BaseServiceImpl<TbOrderShipping, TbOrderShippingExample, Long> implements TbOrderShippingService {

    @Autowired
    public void setBaseMapper(TbOrderShippingMapper mapper) {
        this.baseMapper = mapper;
    }
}
