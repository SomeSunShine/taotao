/*package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void test1(){
		//创建一个jedis对象
		Jedis jedis=new Jedis("192.168.182.130",6379);
		//调用jedis对象的方法,方法名称和redis的命令一致
		jedis.set("key1", "jedis test");
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis
		jedis.close();
	}
	
	*//**
	 * 使用连接池
	 *//*
	@Test
	public void testPool(){
		//创建连接池
		JedisPool pool=new JedisPool("192.168.182.130",6379);
		//从连接池获得jedis对象
		Jedis jedis=pool.getResource();
		String string=jedis.get("key1");
		System.out.println(string);
		
		jedis.close();
		pool.close();
	}
	
	
	@Test
	public void testCluster(){
		HashSet<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.182.130", 7001));
		nodes.add(new HostAndPort("192.168.182.130", 7002));
		nodes.add(new HostAndPort("192.168.182.130", 7003));
		nodes.add(new HostAndPort("192.168.182.130", 7004));
		nodes.add(new HostAndPort("192.168.182.130", 7005));
		nodes.add(new HostAndPort("192.168.182.130", 7006));
		
		JedisCluster cluster=new JedisCluster(nodes);
		cluster.set("key1", "10000");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close();
	}
	
	
	*//**
	 * 单机版
	 * @Title: testSpringJedisSingle 
	 * @Description: TODO
	 * @return: void
	 *//*
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis=pool.getResource();
		String string=jedis.get("key1");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	*//**
	 * 集群版
	 * @Title: testSpringJedisCluster 
	 * @Description: TODO
	 * @return: void
	 *//*
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
		String string=jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
	}
	
}
*/