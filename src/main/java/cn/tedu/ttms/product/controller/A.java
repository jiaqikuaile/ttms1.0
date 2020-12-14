package cn.tedu.ttms.product.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class A {
	private HttpServletResponse _response;
	private HttpServletRequest _request;
public static void main(String[] args) throws Exception {
	String str="F://SUT PO SO 签核导出 (47).pdf";
	//String str="F://CRM对接EAS接口文档_0213_v1.0.10.docx";
	File file=new File(str);
	//System.out.println(file.exists());
	/*System.out.println(file.exists());
	PDDocument doc=PDDocument.load(file);
	PDFRenderer renderer=new PDFRenderer(doc);
	int pageCount =doc.getNumberOfPages();
	System.out.println(pageCount);
	for(int i=0;i<pageCount;i++){
		BufferedImage image=renderer.renderImageWithDPI(i, 296);
		//BufferedImage image= renderer.renderImageWithDPI(i, 300, ImageType.RGB);
		System.out.println(ImageType.RGB);
		File imageFile=File.createTempFile("temp", ".png", new File("F://tt"));
		ImageIO.write(image, "PNG", imageFile);
	}*/
	//File ff=convertPdfToPng(file);
	/*FileOutputStream fos=new FileOutputStream("F://tt.pdf");

	FileInputStream fin=new FileInputStream(ff);
	
	byte[] bb=new byte[1024];
	int a=0;
	while((a=fin.read(bb))!=-1){
		fos.write(bb, 0, a);
	}
	fin.close();
	fos.close();*/
	/*List detailList = new ArrayList();
	Map pageData = new HashMap();
	pageData.put("pageInputStream", null);
	detailList.add(pageData);
	Map<String, Object> parameters = new HashMap<String,Object>();//头部文件
	//Iterator<P12335doExport> countHeader=tempfile.iterator();
	
					
		parameters.put("countPoAmount","1");
		parameters.put("venderName", "2");//供应商
		parameters.put("receiveStore", "3");
		parameters.put("countOrderQty", "4");
		parameters.put("supplierNameFind", "5");//供应商
		parameters.put("orderCode", "6");//订单编号
		Date orderDate = new Date();
		String orderDateStr="";
		
		parameters.put("orderDate", orderDateStr);//订单日期
		parameters.put("paymentName", "8");//付款条件
		parameters.put("feeCode","9");//PRICE TERM
		parameters.put("currency", "RMB");//币别
		parameters.put("monthCount", "36");//保固期
		
    String jasperPath="F://demo//P12333_zh_CN.jasper" ;
	File jasperFile = new File(jasperPath);
	JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);
	//JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport,parameters,new JRBeanCollectionDataSource(detailList));
	JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport,parameters,new JRBeanCollectionDataSource(detailList,false));
	String fileName = "SUT PO SO签核" + ".pdf";
	A a=new A();
	a.responseReportFile(jasperPrint, fileName);*/

	 /*FTPClient  ftpClient= new FTPClient();
	 ftpClient.connect("10.130.217.180", 21);
	boolean s= ftpClient.login("KF", "123foxconn$");
	int replyCode = ftpClient.getReplyCode();
    boolean ss=FTPReply.isPositiveCompletion(replyCode);
     System.out.println(ss+" "+replyCode);
     ftpClient.enterLocalPassiveMode();
     ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
     ftpClient.makeDirectory("F://demo");
     ftpClient.changeWorkingDirectory("/123");
     ftpClient.storeFile("rjq", inputStream);
     ftpClient.retrieveFile8*/
     
     
	
}
protected  void responseReportFile(JasperPrint jasperPrint, String fileName) throws Exception {
	File tempfile = File.createTempFile("as", ".pdf",new File("F://demo"));
	FileOutputStream fos = new FileOutputStream(tempfile);
	JRPdfExporter exporter = new JRPdfExporter();
	exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, fos);
	exporter.exportReport();
	//
	String mimeType= "application/pdf";
	responseFile(tempfile, fileName, mimeType);
	tempfile.delete();
}

protected  void responseFile(File tempfile, String fileName, String mimeType) throws Exception {
	FileInputStream inputStream = new FileInputStream(tempfile);
	//String mimeType= URLConnection.guessContentTypeFromName(fileName);
	if(fileName == null) {
		fileName = tempfile.getName();
	}
	if (mimeType == null) {
		mimeType = javax.activation.FileTypeMap.getDefaultFileTypeMap().getContentType(tempfile);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
	}
	_response.setContentType(mimeType);
	_response.setContentLength((int) tempfile.length());
	//
	String header=_request.getHeader("user-agent");
	String encodedName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
	if(header.contains("AppleWebKit") 
			|| header.contains("Chrome")
			|| header.contains("Safari")
			|| header.contains("Firefox")) {
		encodedName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
	}
	else {
		encodedName = java.net.URLEncoder.encode(fileName,"UTF-8");
		encodedName = encodedName.replace("%20","+");
	}
	String headerKey = "Content-Disposition";
	String headerValue = String.format("attachment; filename=\"%s\"", encodedName);
	//String headerValue = String.format("attachment; filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
	//String headerValue = String.format("attachment; filename=\"%s\"", java.net.URLEncoder.encode(fileName, "UTF-8"));
	System.out.println("headerValue: " + headerValue);
	_response.setHeader(headerKey, headerValue);
	_response.setHeader("x-filename", encodedName);
	OutputStream outStream = _response.getOutputStream();
	byte[] buffer = new byte[1024];
	int bytesRead = -1;
	// write bytes read from the input stream into the output stream
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outStream.write(buffer, 0, bytesRead);
	}
	inputStream.close();
	outStream.close();
}
public static File convertPdfToPng(File pdf) throws Exception {
	//logger.info("convertPdfToPng() pdf=" + pdf.getAbsolutePath());
	PDDocument doc = PDDocument.load(pdf);
	PDFRenderer renderer = new PDFRenderer(doc);
	int pageCount = doc.getNumberOfPages();
	//logger.info(">>>pageCount:" + pageCount);
	//
	File tmp = File.createTempFile("tmp", ".png",new File("F://demo"));
	FileOutputStream fos = new FileOutputStream(tmp);
	//
	BufferedImage imageResult = null;
	//int width = 0;
	int[] singleImgRGB;
	int shiftHeight = 0;
	int maxImageHeight = 0;
	int maxImageWidth = 0;
	List<BufferedImage> imgList = new ArrayList<BufferedImage>();
	for(int i = 0; i < pageCount; i++) {
		//logger.info("pageCount=" + i);
		BufferedImage image = renderer.renderImageWithDPI(i, 296);
		//logger.info("imageHeight=" + image.getHeight() + ", imageWidth=" + image.getWidth());
		int imageHeight = image.getHeight();
		maxImageHeight = maxImageHeight + imageHeight;
		if(image.getWidth() > maxImageWidth) {
			maxImageWidth = image.getWidth();
		}
		imgList.add(image);
	}
	//logger.info("maxImageWidth=" + maxImageWidth + ", maxImageHeight=" + maxImageHeight);
	for(int i = 0; i < pageCount; i++) {
		//logger.info("pageCount=" + i);
		BufferedImage image = imgList.get(i);//BufferedImage image = renderer.renderImageWithDPI(i, 296);
		int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();
       // logger.info("imageHeight=" + imageHeight + ", imageWidth=" + imageWidth);
        if(i == 0) {
        	imageResult = new BufferedImage(maxImageWidth, maxImageHeight, BufferedImage.TYPE_INT_RGB);
        }
		singleImgRGB = image.getRGB(0, 0, imageWidth, imageHeight, null, 0, imageWidth);
		imageResult.setRGB(0, shiftHeight, imageWidth, imageHeight, singleImgRGB, 0, imageWidth);
		shiftHeight += imageHeight;
		if(i >= 10) break; 
	}
	ImageIO.write(imageResult, "PNG", fos);
	imageResult.flush();
	fos.flush();
	fos.close();
	//logger.info("tmp=" + tmp.getAbsolutePath());
	doc.close();
	singleImgRGB = null;
	imgList = null;
	return tmp;
}
}
