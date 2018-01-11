package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.TbContentCategoryService;

@Service
public class TbContentCategoryServiceImpl extends BaseServiceImpl<TbContentCategory, TbContentCategoryExample, Long> implements TbContentCategoryService {

    @Autowired
    public void setBaseMapper(TbContentCategoryMapper mapper) {
        this.baseMapper = mapper;
    }
}
