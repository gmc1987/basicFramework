package com.basic.framework.platform.basic.common.utils;

import java.security.MessageDigest;

public class UtilMD5 {
	public final static String MD5(String pwd) {
		//用于加密的字符
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			//使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
			byte[] btInput = pwd.getBytes();
			// 获得指定摘要算法的 MessageDigest对象，此处为MD5
			//MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			//信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。 
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			//MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
			mdInst.update(btInput);
			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
			byte[] md = mdInst.digest();
			
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = md5String[byte0 >>> 4 & 0xf]; 
				str[k++] = md5String[byte0 & 0xf];
			}
			//返回经过加密后的字符串
			return new String(str);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final static String getMD5(String s) throws Exception {
	    StringBuffer result = null;
	    try {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(s.getBytes("UTF8"));
	      byte b[] = md.digest();
	      result = new StringBuffer();
	      for (int i = 0; i < b.length; i++) {
	        result.append(Integer.toHexString( (0x000000ff & b[i]) | 0xffffff00).
	                      substring(6));
	      }
	
	      return result.toString();
	    }
	    catch (Exception e) {
	      throw e;
	    }
    }
	public static void main(String[] args) throws Exception {
		System.out.println(UtilMD5.MD5("Angel_870223"));
		System.out.println(getMD5("Angel_870223"));
//		75D34D27F9F16D5068EFE682EE051421
		
	}
}
