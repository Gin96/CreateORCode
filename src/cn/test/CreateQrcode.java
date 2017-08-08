package cn.test;

import cn.util.QrcodeUtil;
/**
 * 生成二维码
 * @author Administrator
 *
 */
public class CreateQrcode {

	public static void main(String[] args) {
		//参数一，二维码扫描后的内容；参数二，生成的二维码存放路径
		QrcodeUtil.getQrcodeImg("二维码内容","D:/img.png");
	}
}
