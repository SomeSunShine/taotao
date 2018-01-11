package com.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.util.FtpUtil;

public class FTPTest {
	
	@Test
	public void testFtpClient() throws Exception{
		//创建一个ftp对象
		FTPClient ftpClient=new FTPClient();
		//创建一个ftp连接
		ftpClient.connect("192.168.182.210",21);
		
		//登录ftp服务
		ftpClient.login("ftproot", "123456");
		//读取本地文件
		FileInputStream inputStream=new FileInputStream(new File("E:\\我的思维导图\\工作文档\\我的资料库\\02.思维导图\\ssm&h\\img\\框架流程图\\MVC模型 .jpg"));
		//设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftproot/www/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//上传文件
		ftpClient.storeFile("2.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
	
	@Test
	public void testUtil() throws Exception {
		FileInputStream fileInputStream=new FileInputStream(new File("E:\\我的思维导图\\工作文档\\我的资料库\\02.思维导图\\ssm&h\\img\\框架流程图\\springMVC架构图.png"));
		FtpUtil.uploadFile("192.168.182.210", 21, "ftproot", "123456", "/home/ftproot/www/images", "/2017/11/07", "1.png", fileInputStream);
	}
}
