package com.kingsoft.studentms.basic;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @基础类
 * @author Administrator
 *
 */
public class BasicService {

	/**
	 * @多文件上传
	 * @param file
	 * @return string
	 * @throws IOException
	 */
	public String uploadMutiFile(MultipartFile file, String path) throws IOException {
		System.out.println("上传的文件" + file);
		if (file.isEmpty()) // 判断文件不能为空
			return "文件不能为空";
		String fileName = file.getOriginalFilename();// 文件原名称
		System.out.println("fileName-------" + fileName);
		String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		System.out.println("fileType-------" + fileType);
		if (fileType.equals(".zip") || fileType.equals(".rar")) {
			// 数据准备
			Date date = new Date();
			fileName = String.valueOf(date.getTime()) +"_"+URLEncoder.encode(fileName,"UTF-8") ;//fileName; // 得到时间戳
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
			System.out.println("文件成功上传到指定目录下");
			return path + "\\" + fileName; // 返回存储路径及文件名
		} else
			return "请确保上传文件后缀为.zip或.rar";
	}
}
