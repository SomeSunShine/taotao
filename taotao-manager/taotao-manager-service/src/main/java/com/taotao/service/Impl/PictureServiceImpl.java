package com.taotao.service.Impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.util.FtpUtil;
import com.taotao.util.IDUtils;

/**
 * 图片上传服务
 * 
 * @ClassName: PictureServiceImpl
 * @Description: TODO
 * @author: guoWD
 * @date: 2017年11月7日 下午11:02:42
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap=new HashMap<>();
		try {
			// 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			
			// 生成新文件名
			// UUID.randomUUID();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String imagePath=new DateTime().toString("/yyyy/MM/dd");
			boolean result= FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
					imagePath, newName, uploadFile.getInputStream());
			// 返回结果
			if (!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "上传失败!");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
			return resultMap;
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常!");
			return resultMap;
		}

	}

}
