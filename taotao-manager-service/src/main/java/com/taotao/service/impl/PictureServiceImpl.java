package com.taotao.service.impl;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService{
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FPT_USERNAME}")
	private String FPT_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	

	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) {
		PictureResult pictureResult = null;
		try {
			String oldName = uploadFile.getOriginalFilename();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FPT_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
					imagePath, newName, uploadFile.getInputStream());
			pictureResult = new PictureResult();
			if(!result){
				pictureResult.setError(1);
				pictureResult.setMessage("文件上传失败");
				return pictureResult;
			}
			pictureResult.setError(0);
			pictureResult.setUrl(IMAGE_BASE_URL + imagePath + "/" + newName);
			pictureResult.setMessage("SUCCESS");
		} catch (IOException e) {
			pictureResult.setError(1);
			pictureResult.setMessage("文件上传异常");
		}
		return pictureResult;
	}
}
