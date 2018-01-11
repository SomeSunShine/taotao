package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.util.JsonUtils;

/**
 * 接受MultiPartFile对象. 调用service返回json格式数据. 
 * @ClassName: PictureController 
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月7日 下午11:49:47  
 */
@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody	//作用: 调用ResponseBody对象的read()方法,往客户端写值. 但是如果返回的是一个pojo时,是无法写到客户端的,此时springmvc会将对象默认的转化为json字符串.
					//如果直接返回一个字符串,springmvc将不用再解析,直接写到客户端. 返回string与返回pojo的区别在于content-type不同,前者为text/plain,后者为appliction...;
	public String pictureUpload(MultipartFile uploadFile){
		Map result=pictureService.uploadPicture(uploadFile);
		//为了保证浏览器兼容性.需将Result转换成json格式的字符串.
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
