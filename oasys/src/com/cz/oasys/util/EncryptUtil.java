package com.cz.oasys.util;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	//�÷�����������޼����ı���ʹ��md5�㷨���м���
	public static String encryptMd5(String password){
		String result = "";
		try {
			//��ϢժҪ
			MessageDigest md5 = 
					MessageDigest.getInstance("MD5");
			
			byte[] temp = 
					md5.digest(password.getBytes("utf-8"));
			
			BASE64Encoder base64en = new BASE64Encoder();
			
			result = base64en.encode(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
