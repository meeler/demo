package cn.meeler.test.controller.qcc;

import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Md5大写加密的工具类
 * @author mozhe
 *
 */
public class Md5Utils {

	public static String encodeMd5(String input) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			md5.update(input.getBytes("utf-8"));
			byte[] b = md5.digest();
			StringBuilder sb = new StringBuilder();
			for(int i =0;i<b.length;i++) {
				String s = Integer.toHexString(b[i]&0xFF);
				if (s.length()==1) {
					sb.append("0");
				}
				sb.append(s.toUpperCase());
			}
			return sb.toString();
		} catch (Exception e) {
			
		}
		return input;
	}
	public static String fileMd5(File file) {
		FileInputStream fileInputStream = null;
		try {
			if (file.exists()) {
				MessageDigest MD5 = MessageDigest.getInstance("MD5");
				fileInputStream = new FileInputStream(file);
				byte[] buffer = new byte[8192];
				int length;
				while ((length = fileInputStream.read(buffer)) != -1) {
					MD5.update(buffer, 0, length);
				}
				return new String(Hex.encodeHex(MD5.digest()));
			}
			throw new RuntimeException("获取文件MD5失败！");
		} catch (Exception e) {
			throw new RuntimeException("获取文件MD5失败", e);
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("关闭文件流失败", e);
			}
		}
	}
}
