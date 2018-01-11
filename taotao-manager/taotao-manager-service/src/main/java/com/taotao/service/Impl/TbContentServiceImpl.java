package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.TbContentService;

@Service
public class TbContentServiceImpl extends BaseServiceImpl<TbContent, TbContentExample, Long> implements TbContentService {

    @Autowired
    public void setBaseMapper(TbContentMapper mapper) {
        this.baseMapper = mapper;
    }
}
