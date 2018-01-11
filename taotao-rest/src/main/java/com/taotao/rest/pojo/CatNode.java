package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * data的  u,n,i 节点
 * @ClassName: CatNode 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月10日 下午4:39:25  
 */
public class CatNode {
	@JsonProperty("u")	//改变key值为u
	private String url;
	@JsonProperty("n")
	private String name;
	@JsonProperty("i")
	private List<?> item;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getItem() {
		return item;
	}

	public void setItem(List<?> item) {
		this.item = item;
	}

}
