package jinhe.lt.security;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5 {

	private final static Logger log = Logger.getLogger(MD5.class);

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}


	/**
	 * 根据指定的加密方式实现单向加密，加密方式有"MD5","SHA-1"
	 * 
	 * @param password
	 * @param algorithm
	 *            "MD5"/"SHA-1"等
	 * 
	 * @return 加密的password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();// getBytes("UTF-8"));

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("用 " + algorithm + " 加密时出错，Exception: " + e);
			return password;
		}
		md.reset();
		md.update(unencodedPassword);

		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5Encode("jinpujun"));

		System.out.println(encodePassword("123456", "MD5"));
		System.out.println(encodePassword("jinpujun", "MD5"));
		System.out.println(encodePassword("jinpujun", "SHA-1"));
	}
}
