package com.kingsoft.studentms.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeMd5 {

	/**
	 * @加密位32位小写
	 * @param plainText
	 * @return
	 */
	public static String enMD5L32(String plainText) {
		String result = null;
		try {
			//定义一个MD5方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//按字节获取输入的字符串
			byte[] btInput = plainText.getBytes();
			md5.update(btInput);
			byte[] btResult = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : btResult) {
				int bt = b & 0xff;
				if (bt < 16) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(bt));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @MD5加密成大写的32位字符串
	 * @param plainText
	 * @return
	 */
	public static String enMD5U32(String plainText) {
		String result = enMD5L32(plainText);
		return result.toUpperCase();
	}
}
