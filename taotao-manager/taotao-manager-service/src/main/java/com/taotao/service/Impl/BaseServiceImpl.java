package com.taotao.service.Impl;

import java.util.List;

import com.taotao.mapper.BaseMapper;
import com.taotao.service.BaseService;



/**
 * ServiceImpl -- service基本实现类
 *  
 * @ClassName: BaseServiceImpl 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年10月31日 下午2:50:52 
 * @param <T>
 * @param <E>
 * @param <K> 
 */
public abstract class BaseServiceImpl<T, E, K> implements BaseService<T, E, K> {
	
	protected BaseMapper<T,E,K> baseMapper;
	
	@Override
	public int countByExample(E e){
		return baseMapper.countByExample(e);
	}

	@Override
	public void insert(T t) {
		baseMapper.insert(t);
		
	}

	@Override
	public void insertSelective(T t) {
		baseMapper.insertSelective(t);
		
	}

	@Override
	public void deleteByPrimaryKey(K k) {
		baseMapper.deleteByPrimaryKey(k);
		
	}

	@Override
	public void deleteByExample(E e) {		
		baseMapper.deleteByExample(e);
	}

	/* 
	 * 根据主键查
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param k
	 * @return 
	 * @see com.taotao.service.BaseService#selectByPrimaryKey(java.lang.Object) 
	 */
	@Override
	public T selectByPrimaryKey(K k) {
		return baseMapper.selectByPrimaryKey(k);
	}

	/* 
	 * 根据查询条件查
	 * 
	 * 例:
     * 		TbItemExample example=new TbItemExample();
     * 		Criteria criteria=example.createCriteria();  //创建查询条件
     * 		criteria.andIdEqualTo(参数);
     * 		List<Object> list=iteamMapper.selectByExample(example);
	 * 
	 * @Title: selectByExample
	 * @Description: TODO
	 * @param e
	 * @return 
	 * @see com.taotao.service.BaseService#selectByExample(java.lang.Object) 
	 */
	@Override
	public List<T> selectByExample(E e) {
		return baseMapper.selectByExample(e);
	}
	
	
	@Override
	public void updateByExampleSelective(T t, E e){
		baseMapper.updateByExampleSelective(t, e);
	}

	@Override
	public void updateByExample(T t, E e) {
		baseMapper.updateByExample(t, e);
		
	}

	@Override
	public void updateByPrimaryKeySelective(T t) {
		baseMapper.updateByPrimaryKeySelective(t);
		
	}

	@Override
	public void updateByPrimaryKey(T t) {
		baseMapper.updateByPrimaryKey(t);
		
	}

	public void setBaseMapper(BaseMapper<T, E, K> baseMapper) {
		this.baseMapper = baseMapper;
	}
		
}
