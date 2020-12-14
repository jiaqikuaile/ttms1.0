package cn.tedu.ttms.product.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ShiBie {
  public static void main(String[] args) throws Exception {
	  ShiBie s=new ShiBie();
	  s.QREncode();
}
	 public static void QREncode() throws Exception {
		    String content = "个人博客：https://www.cnblogs.com/huanzi-qch/";//二维码内容
	        int width = 200; // 图像宽度
	        int height = 200; // 图像高度
	        String format = "gif";// 图像类型
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
	        //内容编码格式
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	        // 指定纠错等级
	        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	        //设置二维码边的空度，非负数
	        hints.put(EncodeHintType.MARGIN, 1);
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
		    BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig), new File("C:\\Users\\user\\Desktop\\123.png"));
   //       BufferedImage bufferedImage = LogoMatrix(toBufferedImage(bitMatrix), new File("D:\\logo.png"));
            ImageIO.write(bufferedImage, "png", new File("F:\\demo\\dddd.png"));//输出带logo图片
            System.out.println("输出成功.");
		 
	 }
	  /**
	     * 二维码添加logo
	     * @param matrixImage 源二维码图片
	     * @param logoFile logo图片
	     * @return 返回带有logo的二维码图片
	     * 参考：https://blog.csdn.net/weixin_39494923/article/details/79058799
	     */
	    public static BufferedImage LogoMatrix(BufferedImage matrixImage, File logoFile) throws Exception{
	        /**
	         * 读取二维码图片，并构建绘图对象
	         */
	        Graphics2D g2 = matrixImage.createGraphics();

	        int matrixWidth = matrixImage.getWidth();
	        int matrixHeigh = matrixImage.getHeight();

	        /**
	         * 读取Logo图片
	         */
	        BufferedImage logo = ImageIO.read(logoFile);

	        //开始绘制图片
	        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制
	        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	        g2.setStroke(stroke);// 设置笔画对象
	        //指定弧度的圆角矩形
	        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);
	        g2.setColor(Color.white);
	        g2.draw(round);// 绘制圆弧矩形

	        //设置logo 有一道灰色边框
	        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	        g2.setStroke(stroke2);// 设置笔画对象
	        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);
	        g2.setColor(new Color(128,128,128));
	        g2.draw(round2);// 绘制圆弧矩形

	        g2.dispose();
	        matrixImage.flush() ;
	        return matrixImage ;
	    }
}
