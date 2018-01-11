package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface PictureService {
	@SuppressWarnings("unchecked")
	Map uploadPicture(MultipartFile uploadFile);
}
