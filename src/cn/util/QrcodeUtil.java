package cn.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * 生成二维码的工具类
 * @author Administrator
 *
 */
public class QrcodeUtil {
	
	public static void getQrcodeImg(String content,String imgPath){
		
		//实例化一个Qrcode对象 生成二维码
		Qrcode qcQrcode = 
				new Qrcode();
		//设置编码N（数字）值为A则代表a~z,B代表其他字符
		qcQrcode.setQrcodeEncodeMode('B');
		//拍错率15%空间,纠错等级
		qcQrcode.setQrcodeErrorCorrect('M');
		//二维码版本
		qcQrcode.setQrcodeVersion(15);
		
		int width = 235;//在调整大小时注意，公式调整，否则不能完整覆盖空白，公式：67+12*(版本号-1)
		int height = 235;
		
		//准备画二维码
		//画板
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//画笔
		Graphics2D gs = image.createGraphics();
		//绘制
		//设置背景颜色
		gs.setBackground(Color.white);
		//设置矩形
		gs.clearRect(0, 0, width, height);
		//设置绘制颜色
		gs.setColor(Color.black);
		
		
		
		try {
			//根据内容来绘制二维码
			//处理信息 异常处理
			byte[] codeOut;
			codeOut = content.getBytes("utf-8");
			boolean[][] code = qcQrcode.calQrcode(codeOut);
			//遍历这个二维数组 判断 真假
			for(int i= 0; i < code.length; i++){
				for(int j = 0;j < code.length; j++){
					if(code[j][i]){
						//如果真就绘制小矩形
						gs.fillRect(j*3+2, i*3+2, 3, 3);
					}
				}
			}
			//释放资源
			gs.dispose();
			image.flush();
			
			//存到本地
			ImageIO.write(image, "png", new File(imgPath));
			System.out.println("生成二维码！");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
