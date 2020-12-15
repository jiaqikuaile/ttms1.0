package cn.tedu.ttms.attachement.controller;

import java.io.File;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class A {
public static void main(String[] args) throws Exception {
	Properties prop = new Properties();
	prop.put("name", "jxl");
	prop.put("age", 25);
	System.out.println(prop.get("name"));
	System.out.println(prop);
	AttachementController a=new AttachementController();
	Class cls=Class.forName("cn.tedu.ttms.attachement.controller.AttachementController");
	//System.out.println(A.class.getClassLoader().toString());
	InputStream is = A.class.getResourceAsStream("AttachementController.class");//AttachementController.java
	InputStream iss = A.class.getClassLoader().getResourceAsStream("cn/tedu/ttms/attachement/controller/AttachementController.class");//"jdbc.properties");
	File fii=new File("D://test//jx.txt");
	 System.out.println(is);
	System.out.println(iss);
	int ad=iss.read();
    System.out.println(ad);
}//***
}
