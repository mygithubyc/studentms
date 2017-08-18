package com.kingsoft.studentms.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadService {
	public static void download(String fileName, String filePath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 声明本次下载状态的记录对象
		DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
		// 设置响应头和客户端保存文件名
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 告诉浏览器下载文件
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		// 用于记录以完成的下载的数据量，单位是byte
		long downloadedLength = 0l;
		try {
			// 打开本地文件流文件路径在包括文件名即后缀，否则会报403错误
			InputStream inputStream = new FileInputStream(filePath + "\\" + fileName);
			// 激活下载操作
			OutputStream os = response.getOutputStream();
			// 循环写入输出流
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
				downloadedLength += b.length;
			}

			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (Exception e) {
			downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
			throw e;
		}
		downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
		downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
		downloadRecord.setLength(downloadedLength);
		// 存储记录
	}

	/**
	 * @多文件下载
	 * @param fileName
	 * @param filePath
	 * @param request
	 * @param response
	 */
	public static void mutiDownload(String fileName, String filePath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 对文件名进行处理
		fileName = fileName.substring(0, fileName.lastIndexOf(",")); // 删除最后一个空格
		System.out.println("fileName:" + fileName);
		String[] fileNames = fileName.split(","); // 得到下载文件数组
		String zipNmae = "myFile.zip"; // 设置附件下载名
		zipNmae = URLEncoder.encode(zipNmae, "UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("multipart/form-data");
		//response.setContentType("application/octet-stream");
		//response.setContentType("application/x-www-form-urlencoded");
		response.setHeader("Content-Disposition", "attachment;fileName=" + zipNmae);	//下载文件命名为zipName

		// 定义压缩流
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		zos.setMethod(ZipOutputStream.DEFLATED);
		// 循环查找文件
		for (String fileNameTemp : fileNames) {
			fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			zipFile(filePath + "\\" + fileNameTemp, zos, request);
			response.flushBuffer();
		}
		zos.close();
	}

	// 压缩文件方法流
	public static void zipFile(String fileName, ZipOutputStream out, HttpServletRequest request) throws IOException {
		File file = new File(fileName);
		if (file.exists()) {
			// 用于记录以完成的下载的数据量，单位是byte
			long downloadedLength = 0l;
			// 声明本次下载状态的记录对象
			DownloadRecord downloadRecord = new DownloadRecord(fileName, fileName.substring(fileName.lastIndexOf("\\")),
					request);
			try {
				byte[] buffer = new byte[1024];
				FileInputStream fis = new FileInputStream(file); // 拿到文件输入流
				out.putNextEntry(new ZipEntry(file.getName())); // 得到文件名
				int len = 0;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
					downloadedLength += len;
				}
				out.flush();
				out.closeEntry();
				fis.close();
			} catch (Exception e) {
				// TODO: handle exception
				downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
				throw e;
			}
			downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
			downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
			downloadRecord.setLength(downloadedLength);
			// 存储记录
		}
	}
}
