package com.taotao.service;

import java.util.List;


/**
 * 	service 基本接口
 *  
 * @ClassName: BaseService 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年10月31日 下午2:49:49 
 * @param <T>
 * @param <E>
 * @param <K> 
 */
public interface BaseService<T,E,K> {
	
	int countByExample(E e);
	
	void insert(T t);
	void insertSelective(T t);
	
	void deleteByPrimaryKey(K k);
	void deleteByExample(E e);
	 		
	T  selectByPrimaryKey(K k);
	
	List<T> selectByExample(E e);
	
	void updateByExampleSelective(T t, E e);

    void updateByExample(T t, E e);

    void updateByPrimaryKeySelective(T t);

    void updateByPrimaryKey(T t);
	
	
}
