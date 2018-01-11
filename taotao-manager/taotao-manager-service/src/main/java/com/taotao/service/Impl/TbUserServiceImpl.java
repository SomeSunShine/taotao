package com.taotao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.service.TbUserService;

@Service
public class TbUserServiceImpl extends BaseServiceImpl<TbUser, TbUserExample, Long> implements TbUserService {

    @Autowired
    public void setBaseMapper(TbUserMapper mapper) {
        this.baseMapper = mapper;
    }
}
