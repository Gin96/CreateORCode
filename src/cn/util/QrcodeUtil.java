package cn.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * ���ɶ�ά��Ĺ�����
 * @author Administrator
 *
 */
public class QrcodeUtil {
	
	public static void getQrcodeImg(String content,String imgPath){
		
		//ʵ����һ��Qrcode���� ���ɶ�ά��
		Qrcode qcQrcode = 
				new Qrcode();
		//���ñ���N�����֣�ֵΪA�����a~z,B���������ַ�
		qcQrcode.setQrcodeEncodeMode('B');
		//�Ĵ���15%�ռ�,����ȼ�
		qcQrcode.setQrcodeErrorCorrect('M');
		//��ά��汾
		qcQrcode.setQrcodeVersion(15);
		
		int width = 235;//�ڵ�����Сʱע�⣬��ʽ�������������������ǿհף���ʽ��67+12*(�汾��-1)
		int height = 235;
		
		//׼������ά��
		//����
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//����
		Graphics2D gs = image.createGraphics();
		//����
		//���ñ�����ɫ
		gs.setBackground(Color.white);
		//���þ���
		gs.clearRect(0, 0, width, height);
		//���û�����ɫ
		gs.setColor(Color.black);
		
		
		
		try {
			//�������������ƶ�ά��
			//������Ϣ �쳣����
			byte[] codeOut;
			codeOut = content.getBytes("utf-8");
			boolean[][] code = qcQrcode.calQrcode(codeOut);
			//���������ά���� �ж� ���
			for(int i= 0; i < code.length; i++){
				for(int j = 0;j < code.length; j++){
					if(code[j][i]){
						//�����ͻ���С����
						gs.fillRect(j*3+2, i*3+2, 3, 3);
					}
				}
			}
			//�ͷ���Դ
			gs.dispose();
			image.flush();
			
			//�浽����
			ImageIO.write(image, "png", new File(imgPath));
			System.out.println("���ɶ�ά�룡");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
