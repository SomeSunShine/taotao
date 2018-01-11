package com.taotao.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.TbItemParamService;
import com.taotao.util.EasyUIDataGridResult;
import com.taotao.util.TaotaoResult;

@Service
public class TbItemParamServiceImpl extends BaseServiceImpl<TbItemParam, TbItemParamExample, Long> implements TbItemParamService {
	
	@Autowired
	TbItemParamMapper tbItemParamMapper;

    @Autowired
    public void setBaseMapper(TbItemParamMapper mapper) {
        this.baseMapper = mapper;
    }

	@Override
	public EasyUIDataGridResult getItemParam(Integer page,Integer rows) {
		//分页
		PageHelper.startPage(page, rows);
		//设置查询参数
		TbItemParamExample example=new TbItemParamExample();
		//执行查询
		List<TbItemParam> list=tbItemParamMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		//封装数据
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		if (list!=null&&list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		tbItemParamMapper.insert(itemParam);
		
		return TaotaoResult.ok();
	}
	
	
}
