package com.kingsoft.studentms.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kingsoft.studentms.model.DownloadRecord;
import com.sun.jmx.snmp.Timestamp;

public class DownloadServiceImpl {
	public static void download(String fileName,String filePath,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
//		声明本次下载记录的对象
		DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
//		设置响应头
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
//		记录完成下载的数据量  单位是byte
		long downloadedLength = 0l;
		File file = new File(filePath + "\\" + fileName);
		if(!file.exists()){
			System.out.println("文件不存在");
		}else{
			System.out.println("前往下载:"+filePath+"\\"+fileName);
		}
		try {
//			必须得加上文件路径+文件名 否则报403错误
			InputStream inputStream = new FileInputStream(filePath+"\\"+fileName);
//			激活下载
			OutputStream outputStream = response.getOutputStream();
			
			byte[] b = new byte[2048];
			int length;
			while((length = inputStream.read(b)) > 0){
				outputStream.write(b, 0, length);
				downloadedLength += b.length;
			}
//			关闭输出流和输入流
			inputStream.close();
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
			e.printStackTrace();
		}
		downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
		downloadRecord.setLength(downloadedLength);
		downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
//		存储记录 这里没表省略
	}
}
