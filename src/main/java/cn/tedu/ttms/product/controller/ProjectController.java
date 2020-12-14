package cn.tedu.ttms.product.controller;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@RequestMapping("/project/")
@Controller
public class ProjectController {
	@Resource
    private ProjectService projectService;
	
	@RequestMapping("listUI")
	public String listUI(){
		File fi=new File("E:/123.png");
		String basePath = this.getClass().getResource("/").getPath();
		String basePath1 = this.getClass().getResource("").getPath();
		System.out.println(basePath);
		System.out.println(basePath1);
		fi.deleteOnExit();
		//fi.delete();
		return "product/project_list";//WEB-INF/pages/product/project_list.jsp
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";//WEB-INF/pages/product/project_edit.jsp
	}
	
    //http://localhost:8080/ttms1.0/project/doFindObjects.do
	@RequestMapping("doFindObjects")
	@ResponseBody //ç¨äºå°è¿åçå¯¹è±¡è½¬æ¢ä¸ºjsonä¸²
	public JsonResult doFindObjects(
			String name,
			Integer valid,
			int pageCurrent){
		System.out.println("doFindObjects().pageCurrent="+pageCurrent);
		Map<String,Object> map=
		projectService.findObjects(
				name,valid,pageCurrent);
		//å°è·å¾çæ°æ®å°è£å°JsonResultå¯¹è±¡
		return new JsonResult(map);
	}//JSON string

	/**
	 * var params={
	 * "checkedIds":checkedId,
	 * "valid":valid}
	 * 
	 * */
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(
			String checkedIds,
			Integer valid){
		System.out.println(
				"checkedIds="+checkedIds);
		projectService.validById(
				checkedIds,
				  valid);
		return new JsonResult();
		//this.message="ok"
		//this.state=SUCCESS
	}
	/**æ§è¡æ·»å æä½
	 * var params={"name":"A","code":"tt20170807xxxx",..}
	 * @param entity å¯¹è±¡ä¼å°è£é¡µé¢ä¸ä¼ å¥çåæ°å¼
	 * é¡µé¢ä¸åæ°çåå­åentityå¯¹è±¡ä¸­å±æ§çå¼
	 * ä¸è´æ¶ä¼å®ç°èªå¨æ³¨å¥æä½.
	 * */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			Project entity){
		projectService.saveObject(entity);
		return new JsonResult();
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Project project=
		projectService.findObjectById(id);
		return new JsonResult(project);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			Project entity,
			HttpServletRequest request){
		//åè®¾æç¨æ·ç»å½,å¯ä»¥ä»sessionä¸­è·å¾ç¨æ·ä¿¡æ¯
		/*User user=(User)
		request.getSession()
		.getAttribute("user");
		entity.setModifiedUser(user.getUsername());
		*/
		projectService.updateObject(entity);
		return new JsonResult();
	} 
	
	@RequestMapping(value ="doTest",method = RequestMethod.POST)
	@ResponseBody
	//@RequestBody
	public JsonResult doTest(@RequestBody 	String ss,HttpServletRequest request) throws UnsupportedEncodingException{
		System.out.println(ss);
		//String ssl=new String("你好".getBytes(), "iso-8859-1");
		
		return new JsonResult("{a='1';b='你好'}");
	}
	@RequestMapping(value ="doTest1",method = RequestMethod.GET)
	@ResponseBody
	//@RequestBody
	public JsonResult doTest1(HttpServletRequest httpServletRequest,
            HttpServletResponse response) throws Exception{
		Map data=new HashMap();
		data.put("name", "zs");
		data.put("date", new Date());
		 // img为图片的二进制流
		File file=new File("C:\\Users\\user\\Desktop\\123.png");
		FileInputStream fin=new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
        byte[] buffer = new byte[1024];
        while((len = fin.read(buffer)) != -1){
        	baos.write(buffer, 0, len);
        }
        byte[] datas = null;
        datas = baos.toByteArray();
	   /* httpServletResponse.setContentType("image/png");
	    OutputStream os = httpServletResponse.getOutputStream();
	    os.write(datas);
	    os.flush();
	    os.close();*/
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");      
        toClient.write(buffer);
        fin.close();
        baos.close();
        toClient.flush();
        toClient.close();
        
		return new JsonResult(data)	;
	}
	
	@RequestMapping(value ="doTest2",method = RequestMethod.GET)
	@ResponseBody
	//@RequestBody
	public JsonResult doTest2(HttpServletRequest httpServletRequest,
            HttpServletResponse response) throws Exception{
		  //1.获取要下载的文件的绝对路径
		         String realPath = "C:\\Users\\user\\Desktop\\123.png";
		         //2.获取要下载的文件名
		         String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
		         //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		         response.setHeader("content-disposition", "inline;filename="+"234.png");
		         //4.获取要下载的文件输入流
		       InputStream in = new FileInputStream(realPath);
		        int len = 0;
		         //5.创建数据缓冲区
		         byte[] buffer = new byte[1024];
		         //6.通过response对象获取OutputStream流
		         OutputStream out = response.getOutputStream();
		         //7.将FileInputStream流写入到buffer缓冲区
		         while ((len = in.read(buffer)) > 0) {
		         //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
		             out.write(buffer,0,len);
		         }
		         in.close();
		return null;
	}
}
