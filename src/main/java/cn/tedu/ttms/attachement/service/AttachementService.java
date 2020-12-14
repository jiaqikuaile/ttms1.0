package cn.tedu.ttms.attachement.service;

import org.springframework.web.multipart.MultipartFile;

public interface AttachementService {
    /**实现文件上传*/
	public void uploadObject(String title,
			MultipartFile mFile);
}
