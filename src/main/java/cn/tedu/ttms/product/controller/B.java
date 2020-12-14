package cn.tedu.ttms.product.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;



public class B {
private static final String QR_CODE_IMAGE_PATH = "F://demo//MyQRCode.png";
	
	private static void generateQRCodeImage(String text, int width, int height, String filePath) throws Exception {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		
		Path path = FileSystems.getDefault().getPath(filePath);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		
	}
	
	public static void main(String[] args) throws Exception {
        try {
            generateQRCodeImage("https://www.baidu.com/", 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
		
	}

}
