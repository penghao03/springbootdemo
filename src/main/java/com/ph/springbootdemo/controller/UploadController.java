package com.ph.springbootdemo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	public static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) {

		if (file.isEmpty()) {
			return "文件为空";
		}

		String filename = file.getOriginalFilename();
		ApplicationHome home = new ApplicationHome(getClass());
		File jarFile = home.getSource();
		String jarPath = jarFile.getParentFile().getAbsolutePath();
		System.out.println("jarPath==" + jarPath);
		String filepath = jarPath + "/pdf/";
		File dest = new File(filepath + filename);

		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			file.transferTo(dest);
			return "上传成功";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "上传失败";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "上传失败";
		}

	}

}
