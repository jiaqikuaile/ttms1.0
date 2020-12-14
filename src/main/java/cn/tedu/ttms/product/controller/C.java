package cn.tedu.ttms.product.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class C {

	public static void main(String[] args) {
		C c=new C();
		c.createCode("https://www.baidu.com", "F://demo//A//ss.png");
		
	}
	
	public boolean createCode(String content,String path){
		int width=300;
		int height=300;
		String fromat="png";//保存图片的格式
		//设置额外参数，定要保存到map集合中
		Map map=new HashMap();
		//设置容错率，其中key值就是EncodeHintType的属性
		//容错等级从大到小的顺序L>M>Q>H，当缓存越高，所需的扫描时间就越长，但是准确率越高
		map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
		map.put(EncodeHintType.MARGIN,20);
		map.put(EncodeHintType.CHARACTER_SET,"utf-8");
		//生成二维码的方法
		try{
		 BitMatrix encode=new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,map);
		 
		 Path path1=new File(path).toPath();
		 MatrixToImageWriter.writeToPath(encode,fromat,path1);
		 return true;
		}catch(Exception e){

		}
		return false;
		}
      

}
