package com.lk.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lk.dto.FileDto;

@Controller
public class FileController {
	
	private static final String URL = "http://192.168.1.101:8080/";

	@ResponseBody
	@RequestMapping("/file/upload")
	public FileDto upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request){
		FileDto fileDto = new FileDto();
		
		String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
		
		//文件名
		String fileName = file.getOriginalFilename();
		//System.out.println(fileName);
		String url_path = "image/"+fileName;
		//System.out.println("图片访问uri："+url_path);
		String savePath = path+"/image"; 
		//System.out.println("图片保存地址："+savePath);
		File targetFile = new File(savePath, fileName);
		//System.out.println("访问URL："+URL+url_path);
		try {
			file.transferTo(targetFile);
			fileDto.setSuccess(1);
			fileDto.setMessage("上传成功");
			fileDto.setUrl(URL+url_path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fileDto.setSuccess(0);
			fileDto.setMessage("上传失败");
			e.printStackTrace();
		}
		
		return fileDto;
	}
}
