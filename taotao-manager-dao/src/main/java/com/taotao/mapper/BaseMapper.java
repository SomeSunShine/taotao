package com.taotao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

//Mapper接口	基础Mapper接口
public interface BaseMapper<T,E,K> {
	
	 	int countByExample(E e);

	    int deleteByExample(E e);

	    int deleteByPrimaryKey(K k);

	    int insert(T t);

	    int insertSelective(T t);

	    List<T> selectByExample(E e);
	    
	    List<T> selectByExampleWithBLOBs(E e);

	    T selectByPrimaryKey(K k);

	    int updateByExampleSelective(@Param("record") T t, @Param("example") E e);

	    int updateByExample(@Param("record") T t, @Param("example") E e);

	    int updateByPrimaryKeySelective(T t);

	    int updateByPrimaryKey(T t);
	    
	    
	
}
