package jinhe.lt.ldap.novell;

import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPConnection;

/**
 * @author Jon.King 2006-3-1
 * 
 * 初始化LDAP各个Parameters
 */

@SuppressWarnings("unchecked")
public class LDAPConstants {

	private static LDAPParameters lp = null;

	public LDAPConstants() {
	}

	public static String getLdapBasedn() {
		return lp.getLdapBasedn();
	}

	public static String getLdapHost() {
		return lp.getLdapHost();
	}

	public static String getLdapPasswd() {
		return lp.getLdapPasswd();
	}

	public static int getLdapPort() {
		return lp.getLdapPort();
	}

	public static int getLdapVersion() {
		return lp.getLdapVersion();
	}

	public static String getLdapAdmindn() {
		return lp.getLdapAdmindn();
	}

	public static List getOpOCS() {
		return lp.getOpOCS();
	}

	public static List getOuOCS() {
		return lp.getOuOCS();
	}

	public static String getOpDnPrefix() {
		return lp.getOpDnPrefix();
	}

	public static String getOuDnPrefix() {
		return lp.getOuDnPrefix();
	}

	static {
		try {
			String ldapHost = "192.168.0.6";
			int ldapPort = LDAPConnection.DEFAULT_PORT; // 389
			int ldapVersion = LDAPConnection.LDAP_V3; // 3
			String loginDN = "admin_hz";
			String password = "password";

			lp = new LDAPParameters();
			lp.setLdapAdmindn(loginDN);
			lp.setLdapBasedn("");
			lp.setLdapHost(ldapHost);
			lp.setLdapPort(ldapPort);
			lp.setLdapVersion(ldapVersion);
			lp.setLdapPasswd(password);
			lp.setOpDnPrefix("cn");
			lp.setOuDnPrefix("ou");

			List list = new ArrayList();
			list.add("person");
			list.add("organizationalPerson");
			list.add("inetOrgPerson");
			list.add("dominoPerson");
			list.add("top");
			lp.setOpOCS(list); // 用户所有对象类
			lp.setOuOCS(null);
		} catch (Exception e) {
		}
	}
}
