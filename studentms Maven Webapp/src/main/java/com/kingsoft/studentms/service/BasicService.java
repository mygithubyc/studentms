package com.kingsoft.studentms.service;

import org.springframework.web.multipart.MultipartFile;

public interface BasicService {
	public String uploadMutiFile(MultipartFile file,String path);
}
