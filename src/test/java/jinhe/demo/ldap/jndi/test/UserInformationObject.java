package jinhe.lt.ldap.jndi.test;

import java.util.Properties;

public final class UserInformationObject {

	public static final String FIRST_NAME = "1";

	public static final String LAST_NAME = "2";

	public static final String COMMON_NAME = "3";

	public static final String USER_ID = "4";

	public static final String PASSWORD = "5";

	public static final String EMAIL = "6";

	public static final String PHONE = "7";

	public static final String FAX = "8";

	private Properties props;


	// 用于创建用户时使用
	public UserInformationObject(String firstName, String lastName, String commonName, String userid) {
		props = new Properties();
		props.setProperty(FIRST_NAME, firstName);
		props.setProperty(LAST_NAME, lastName);
		props.setProperty(COMMON_NAME, commonName);
		props.setProperty(USER_ID, userid);
	}

	// 用于修改用户信息时使用
	public UserInformationObject(String userid) {
		props = new Properties();
		props.setProperty(USER_ID, userid);
	}

	// 设置First Name
	public void setFirstname(String firstName) {
		props.setProperty(FIRST_NAME, firstName);
	}

	// 设置Last Name
	public void setLastname(String lastName) {
		props.setProperty(LAST_NAME, lastName);
	}

	// 设置Common Name
	public void setCommonname(String commonName) {
		props.setProperty(COMMON_NAME, commonName);
	}

	// 设置Password
	public void setPassword(String password) {
		props.setProperty(PASSWORD, password);
	}

	// 设置E-Mail
	public void setEmail(String email) {
		props.setProperty(EMAIL, email);
	}

	// 设置Phone
	public void setPhone(String phone) {
		props.setProperty(PHONE, phone);
	}

	// 设置Fax
	public void setFax(String fax) {
		props.setProperty(FAX, fax);
	}

	// 根据属性名得到属性的值
	public String getProperty(String propertyName) {
		return props.getProperty(propertyName);
	}

	// 存储属性的个数
	public int size() {
		return props.size();
	}
}
