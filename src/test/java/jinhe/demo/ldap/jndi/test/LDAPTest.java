package jinhe.lt.ldap.jndi.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

@SuppressWarnings("unchecked")
public final class LDAPTest {

	private DirContext ctx;

	private Hashtable env;

	// 服务器地址
	private static final String LDAP_URL = "ldap://192.168.0.6:389";

	// 管理域的dn
	private static final String MANAGER_DN = "cn=admin_hz";

	// 管理域的密码
	private static final String MANAGER_PASSWORD = "password";

	// 管理域的验证方式
	private static final String AUTH_TYPE = "simple";

	//
	private static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

	// 用于测试的用户组的DN
	private static final String BASE_DN = "cn=student,ou=People,dc=com";

	// 构造函数用于初始化ldap连接和创建用于测试的用户组
	public LDAPTest() throws NamingException {
		setEnvironment();
		ctx = new InitialDirContext(env);
		createGroup();
	}

	// 设置登录到LDAP服务器的信息
	private void setEnvironment() {
		env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, AUTH_TYPE);
		env.put(Context.SECURITY_PRINCIPAL, MANAGER_DN);
		env.put(Context.SECURITY_CREDENTIALS, MANAGER_PASSWORD);
	}

	// 创建一个用于测试的用户组(当用户组不存在时创建)
	private void createGroup() throws NamingException {
		try {
			Attributes attr = ctx.getAttributes(BASE_DN);
            attr.getAll();
		} catch (NamingException ne) {
			Attribute objclass = new BasicAttribute("objectclass");
			objclass.add("top");
			objclass.add("groupofuniquenames");

			Attribute cn = new BasicAttribute("cn", "student");

			Attributes attrs = new BasicAttributes();
			attrs.put(objclass);
			attrs.put(cn);

			ctx.bind(BASE_DN, null, attrs);
			System.out.println("Group created.n");
		}
	}

	// 关闭到LDAP服务器的连接
	private void closeConnection() {
		try {
			ctx.close();
		} catch (NamingException ne) {
			System.out.println(ne);
		}
	}

	// 验证用户是否存在
	private boolean isUserexist(String uid) {
		try {
			Attributes attrs = findUser(uid);
			if (attrs != null) {
				return true;
			} else {
				return false;
			}
		} catch (NamingException ne) {
			return false;
		}
	}

	// 设置属性
	private void putAttribute(Attributes attrs, String attrName, String attrValue) {
		if (attrValue != null && attrValue.length() != 0) {
			Attribute attr = new BasicAttribute(attrName, attrValue);
			attrs.put(attr);
		}
	}

	// 得到属性
	private String getAttribute(Attributes attrs, String attrName)
			throws NamingException {
		Attribute attr = attrs.get(attrName);
		if (attr == null) {
			return "";
		} else {
			return (String) attr.get();
		}
	}

	// 查找用户
	private Attributes findUser(String uid) throws NamingException {
		return ctx.getAttributes("uid=" + uid + "," + BASE_DN);
	}

	// 创建用户
	public void createUser(UserInformationObject userobj)
			throws NamingException {
		// 用户对象为空
		if (userobj == null) {
			throw new NamingException("No user informationn");
		}

		// 检查uid
		String uid = userobj.getProperty(UserInformationObject.USER_ID);
		if (uid == null && uid.length() == 0) {
			throw new NamingException("No uid you specifyn");
		}
		if (isUserexist(uid)) {
			throw new NamingException("The user(uid: " + uid + ") is exist!n");
		}

		// 检查firstName
		String firstName = userobj.getProperty(UserInformationObject.FIRST_NAME);
		if (firstName == null || firstName.length() == 0) {
			throw new NamingException("No first name you specify!n");
		}

		// 检查lastName
		String lastName = userobj.getProperty(UserInformationObject.LAST_NAME);
		if (lastName == null || lastName.length() == 0) {
			throw new NamingException("No last name you specify!n");
		}

		// 检查commonName
		String commonName = userobj.getProperty(UserInformationObject.COMMON_NAME);
		if (commonName == null || commonName.length() == 0) {
			throw new NamingException("No common name you specify!n");
		}

		String password = userobj.getProperty(UserInformationObject.PASSWORD);
		String email = userobj.getProperty(UserInformationObject.EMAIL);
		String phone = userobj.getProperty(UserInformationObject.PHONE);
		String fax = userobj.getProperty(UserInformationObject.FAX);

		Attributes attrs = new BasicAttributes();

		// 设置属性
		Attribute objclass = new BasicAttribute("objectclass");
		objclass.add("top");
		objclass.add("person");
		objclass.add("organizationalPerson");
		objclass.add("inetorgperson");
		attrs.put(objclass);

		putAttribute(attrs, "cn", commonName);
		putAttribute(attrs, "givenname", firstName);
		putAttribute(attrs, "sn", lastName);
		putAttribute(attrs, "uid", uid);
		putAttribute(attrs, "userpassword", password);
		putAttribute(attrs, "mail", email);
		putAttribute(attrs, "telephonenumber", phone);
		putAttribute(attrs, "facsimiletelephonenumber", fax);

		// 添加用户节点
		ctx.bind("uid=" + uid + "," + BASE_DN, null, attrs);

		System.out.println("User(uid: " + uid + ") created.n");

	}

	// 修改用户信息
	public void modifyUser(UserInformationObject userobj)
			throws NamingException {
		// 用户对象为空
		if (userobj == null) {
			throw new NamingException("No user information!n");
		}
		// 检查uid
		String uid = userobj.getProperty(UserInformationObject.USER_ID);
		if (uid == null && uid.length() == 0) {
			throw new NamingException("No uid you specify!n");
		}
		if (!isUserexist(uid)) {
			throw new NamingException("The user(uid: " + uid
					+ ") does not exist!n");
		}

		int size = userobj.size(); // 用户属性的个数

		// 如果属性个数只有一个,那么只设置了uid,不用修改用户属性
		if (size > 1) {
			String password = userobj.getProperty(UserInformationObject.PASSWORD);
			String email = userobj.getProperty(UserInformationObject.EMAIL);
			String phone = userobj.getProperty(UserInformationObject.PHONE);
			String fax = userobj.getProperty(UserInformationObject.FAX);
			String commonName = userobj.getProperty(UserInformationObject.COMMON_NAME);
			String firstName = userobj.getProperty(UserInformationObject.FIRST_NAME);
			String lastName = userobj.getProperty(UserInformationObject.LAST_NAME);

			// 设置属性
			Attributes attrs = new BasicAttributes();
			putAttribute(attrs, "cn", commonName);
			putAttribute(attrs, "givenname", firstName);
			putAttribute(attrs, "sn", lastName);
			putAttribute(attrs, "userpassword", password);
			putAttribute(attrs, "mail", email);
			putAttribute(attrs, "telephonenumber", phone);
			putAttribute(attrs, "facsimiletelephonenumber", fax);

			// 修改属性
			ctx.modifyAttributes("uid=" + uid + "," + BASE_DN, DirContext.REPLACE_ATTRIBUTE, attrs);

			System.out.println("User(uid: " + uid + ") information modified.n");
		} else {
			throw new NamingException("No modify information you specify!n");
		}
	}

	// 删除用户
	public void deleteUser(String uid) throws NamingException {
		if (!isUserexist(uid)) {
			throw new NamingException("The user(uid: " + uid + ") does not exist!n");
		}
		ctx.destroySubcontext("uid=" + uid + "," + BASE_DN);
		System.out.println("User(uid: " + uid + ") deleted.n");
	}

	// 根据提供的uid察看用户的信息
	public void selectUser(String uid) throws NamingException {
		Attributes attrs;
		System.out.println("select user(uid: " + uid + ")...");
		try {
			attrs = findUser(uid);
			System.out.println("-----------------------------");
			System.out.println("User(uid: " + uid + ") listing...");

			System.out.println("First Name: " + getAttribute(attrs, "givenname"));
			System.out.println("Last Name: " + getAttribute(attrs, "sn"));
			System.out.println("Common Name: " + getAttribute(attrs, "cn"));
			System.out.println("User ID: " + getAttribute(attrs, "uid"));
			System.out.println("E-Mail: " + getAttribute(attrs, "mail"));
			System.out.println("Phone: " + getAttribute(attrs, "telephonenumber"));
			System.out.println("Fax: " + getAttribute(attrs, "facsimiletelephonenumber"));
			System.out.println("List completed.");
			System.out.println("-----------------------------n");
		} catch (NamingException ne) {
			throw new NamingException("The user(uid: " + uid + ") is not exist!n");
		}
	}

	// 提供一个存有多个用户信息的数组查询多个用户
	public void selectUser(String[] uid) {
		for (int i = 0; i < uid.length; i++) {
			try {
				selectUser(uid[i]);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	protected void shutDown() {
		closeConnection();
	}

}
