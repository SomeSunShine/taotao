/*package com.taotao.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;


*//**
 * 
 *  
 * @ClassName: TestPagehelper 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月2日 下午5:47:36  
 *//*
public class TestPagehelper {
	
	@Test
	public void testPageHelper() throws Exception{
		//1.获得mapper代理对象
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applictionContext-*.xml");
		TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);
		//2.设置分页
		PageHelper.startPage(1, 30);	//只对紧接着的第一个select有效.		<只需要在pom中添加一个插件配置;然后在查询之前写上这句即可>
		//3.执行查询
		TbItemExample example=new TbItemExample();	//设置参数
		List<TbItem> list=itemMapper.selectByExample(example);	//若未设置条件则默认查询所有数据
		//4.取得分页后的结果
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		long total=pageInfo.getTotal();
		System.out.println("total:"+total);
		int pages=pageInfo.getPages();
		System.out.println("pages:"+pages);
		int pagesize=pageInfo.getPageSize();
		System.out.println("pagesize:"+pagesize);
	}
}
*/