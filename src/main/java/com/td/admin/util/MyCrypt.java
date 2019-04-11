package com.td.admin.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Random;

public class MyCrypt {

	private static final String UTF8 = "utf-8";

	// 加密
	public static String myEncode(String src) {
		String s1 = getRandomString();
		byte[] b1 = encrypt(s1.getBytes(), s1);
		String s2 = parseByte2HexStr(b1);
		byte[] b2 = encrypt(src.getBytes(), s2);
		String s3 = parseByte2HexStr(b2);
		return s2 + s3;
	}

	//解密
	public static String myDecode(String code) {
		String k1 = code.substring(0, 64);
		String k2 = code.substring(64);
		try {
			byte[] decryptFrom = parseHexStr2Byte(k2);
			byte[] decryResult = decrypt(decryptFrom, k1);
			return new String(decryResult);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		return cipher.doFinal(src);
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static String getRandomString() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 24; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}


	/**
	 * BASE64编码
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String base64Encoder(String src) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(src.getBytes(UTF8));
	}

	/**
	 * BASE64解码
	 * @param dest
	 * @return
	 * @throws Exception
	 */
	public static String base64Decoder(String dest) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(dest), UTF8);
	}


	public static void main(String[] args) {
		/*找回助记词*/
		/*String mnen="A3B027BDBBB71677D559F7F4415B567464A74E0313CC2C7EE39D85097D7285CA92E49A072A8066FD3B16C4E10B31CB8A6390FD99D05281A8A5EE6DECE3CAE62588A5E5D6E16C380DCE893F7AF622536F5BBD3A811DB9B5277485AC4BFC442F36103A458EEEB69FDD";
		String oMnem = MyCrypt.myDecode(mnen);
		List<String> list=new ArrayList<>();
		String[] s = oMnem.split(" ");
		String newArr="";
		//System.out.println(mnem);
		for(String s1:s){
			System.out.print(s1+" ");
		}
		for (int i = 0; i < 6; i++) {
			list.add(s[i]);
		}
		System.out.println();
		for(String str:list){
			newArr+=str+" ";
		}
		System.out.println(newArr.trim());*/

		//System.out.println(new Timestamp(new Date().getTime()));
		/*try {
			System.out.println(MyCrypt.myEncode("123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
			/*BigDecimal a=new BigDecimal("0.0000");
			BigDecimal b=new BigDecimal("0.0000");

			System.out.println(a.multiply(b));*/
		String pwd="111111";
		System.out.println(myEncode(pwd));
		//0F046C4783A2B9B3C4E79FE8DD4402468F5E9BA1AFA6606658812DB1810D00476858FD1F4334B18E
		System.out.println(myDecode("5568265DEE83F6B9041F96E97702D89F6030B00AE81F366E15FFBDBCD6234FE0070BBF1D7A219144"));
	}
}
