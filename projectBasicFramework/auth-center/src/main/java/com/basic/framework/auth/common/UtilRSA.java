package com.basic.framework.auth.common;


import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
 
import javax.crypto.Cipher;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author yfs
 * @date 2018-12-28
 */
public class UtilRSA {
	//加密算法RSA
	public static final String KEY_ALGORITHM = "RSA";
 
	//签名算法
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
 
	//获取公钥的key
	private static final String PUBLIC_KEY = "RSAPublicKey";
 
	//获取私钥的key
	private static final String PRIVATE_KEY = "RSAPrivateKey";
 
	//RSA最大加密明文大小
	private static final int MAX_ENCRYPT_BLOCK = 117;
 
	//RSA最大解密密文大小
	private static final int MAX_DECRYPT_BLOCK = 128;
 
	//RSA位数
	private static final int INITIALIZE_LENGTH = 1024;
	
	public static final String THIS_PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALobw9vSQ22RI8cEmqqIfibdhYwzbMFMoNlqorNizSEQGeZvw83zNC1soMeeYiSd7JO5ur02aqbsirxSLCS8CQxnXRsK9M3EkKvYGpzCoE5JiVznU3LzAUclk2WBBV+puyAbiryx2Nbz/pYRCk16licxm4AUgHTzlMJHNGGX6Z4RAgMBAAECgYBGAtflQ+4Vs7nvzm2YpYw/dqocmePZu8fuwky+ivZ776CurXDNcyY3yFubfJuTBs3OrNnxAB2S+f0e5xKwJwSHvjjsi3ewoQMb5WIulrDBxVR3FAu0gYB34QOrr1NFVbFi7nM3UfDiRfKkS/IIqlRfA7d25bdNXsDP1Zfe66Y62QJBAPqIl/6XT0zGA8RRsDcqMc6zri6hUY4ZBBxR2dbs/deQ5OvGH2CmPrCVKbv5hF2wlB2nThEZnT14gxA6/Zk9iVsCQQC+K0/Pnjmd3GyNgHzOlqYlanTBGTbVmhmHBzkvyTW4wkilRMB8NHDb4HBQQ84H7oLmMsLf4yp4Di5o4aCZYKYDAkEAt7Ix6D2fqoZCOs8+qqQokwvSOmSUPOGAzxcA6AMq+MVH5MyHM0GSE727NRX2BRA0+i0zI3ype2oCh4MJcTPaowJAIVorRzhy1nLgPvj36quVPB6tl2g6q7+5Fmt35h+d4meAoiuqrp3r5UQ5rnoV2mJEZuckMmv++MtT7GUV65B9zQJBANqJwHw2ompwSXsDWwfMj8KIo5tPJUWYaMKP9VDKbWluZpjUyHfJA+Od7e4+3O299uW2Bf8+Bml1LiM4MLXYfwg=";
	
	public static final String THIS_PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6G8Pb0kNtkSPHBJqqiH4m3YWMM2zBTKDZaqKzYs0hEBnmb8PN8zQtbKDHnmIkneyTubq9Nmqm7Iq8UiwkvAkMZ10bCvTNxJCr2BqcwqBOSYlc51Ny8wFHJZNlgQVfqbsgG4q8sdjW8/6WEQpNepYnMZuAFIB085TCRzRhl+meEQIDAQAB";
	
	/**
	 * 生成密钥对(公钥和私钥)
	 * @param 
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(INITIALIZE_LENGTH);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	/**
	 * 获取私钥
	 * @param keyMap密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}
	
	/**
	 * 获取公钥
	 * @param keyMap密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}
	
	/**
	 * 私钥解密
	 * @param data已加密数据 byte[] Base64.decodeBase64()
	 * @param privateKey私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData,"UTF-8");
	}
	
	/**
	 * 公钥加密
	 * @param data源数据 byte[] .getBytes()
	 * @param publicKey公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return Base64.encodeBase64String(encryptedData);
	}
	
	/**
	 * 用私钥对信息生成数字签名
	 * @param data 已加密数据 byte[] Base64.decodeBase64()
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}
	
	/**
	 * 校验数字签名
	 * @param data已加密数据 byte[] Base64.decodeBase64()
	 * @param publicKey公钥(BASE64编码)
	 * @param sign数字签名
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decodeBase64(sign));
	}
 
	public static void main(String[] args) throws  Exception{
		/*Map<String, Object> keyMap = initKey();
		String privateKey = getPrivateKey(keyMap);
		System.out.println("私钥:"+privateKey);
		String publicKey = getPublicKey(keyMap);
		System.out.println("公钥:"+publicKey);*/
		
		String data = "123456";
		String encryptedData = encryptByPublicKey(data.getBytes(),THIS_PUBLICKEY);
		System.out.println("加密:"+encryptedData);
		
		String decryptData = decryptByPrivateKey(Base64.decodeBase64(encryptedData),THIS_PRIVATEKEY);
		System.out.println("解密:"+decryptData);
	}
}
