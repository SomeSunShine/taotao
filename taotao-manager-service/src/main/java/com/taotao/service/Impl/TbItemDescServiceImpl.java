package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.service.TbItemDescService;

@Service
public class TbItemDescServiceImpl extends BaseServiceImpl<TbItemDesc, TbItemDescExample, Long> implements TbItemDescService {

    @Autowired
    public void setBaseMapper(TbItemDescMapper mapper) {
        this.baseMapper = mapper;
    }
}
