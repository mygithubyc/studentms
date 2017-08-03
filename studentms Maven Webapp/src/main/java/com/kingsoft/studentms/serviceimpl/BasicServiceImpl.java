package com.kingsoft.studentms.serviceimpl;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kingsoft.studentms.service.BasicService;;

public class BasicServiceImpl implements BasicService{

	public String uploadMutiFile(MultipartFile file,String path){
		System.out.println("上传的文件"+file);
		if (file.isEmpty()) {
			return "empty";
		}else {
			
			String fileName = file.getOriginalFilename();
			
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			System.out.println("文件类型"+fileType);
			if (fileType.equals(".zip") || fileType.equals(".rar")) {
				Date date = new Date();
				fileName = String.valueOf(date.getTime())+"_"+fileName;
				System.out.println("保存的文件名"+fileName);
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("文件上传成功");
				return path+"\\"+fileName;
				
			}else {
				return "typeError";
			}
	
			
		}
	}
}
