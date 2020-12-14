package cn.tedu.ttms.attachement.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/attachement/")
public class AttachementController {
	@Resource
	private AttachementService attachementService;
    @RequestMapping("attachementUI")
	public String attachmentUI(){
		return "attachement/attachement";
	}
    /**
     * @param title 为附件标题
     * @param mFile 用于接收上传的附件的对象
     * */
    @RequestMapping("doUpload")
    @ResponseBody
    public JsonResult doUpload(
    		String title,
    		MultipartFile mFile){
    	//原有内容是练习上传,业务要写到service
    	attachementService
    	.uploadObject(title,mFile);
    	return new JsonResult();
    }
}
