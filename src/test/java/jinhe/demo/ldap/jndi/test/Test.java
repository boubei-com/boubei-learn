package jinhe.lt.ldap.jndi.test;

public class Test {
	public static void main(String[] args) {

		// 初始化用户
		UserInformationObject zhangsan = new UserInformationObject("zhang", "san", "zhang san", "zhangsan");
		UserInformationObject lisi = new UserInformationObject("li", "si", "li si", "lisi");

		// 设置用户属性
		zhangsan.setPassword("abcdef");
		zhangsan.setPhone("02866666666");
		zhangsan.setEmail("[email]zhangsan@163.com[/email]");
		zhangsan.setFax("02887654321");

		lisi.setPassword("fedcba");
		lisi.setPhone("02888888888");
		lisi.setEmail("[email]lisi@163.com[/email]");
		lisi.setFax("02812345678");

		try {
			LDAPTest ldap = new LDAPTest();
			// 创建用户
			ldap.createUser(zhangsan);
			ldap.createUser(lisi);

			String[] user = { "wangwu", "zhangsan", "lisi", "zhaoliu" };
			// 察看用户信息
			ldap.selectUser(user);

			// 修改用户
			zhangsan.setPhone("02811111111");
			zhangsan.setEmail("[email]ldap@163.com[/email]");			
			ldap.modifyUser(zhangsan);

			// 察看用户信息
			ldap.selectUser("zhangsan");

			// 删除用户
			ldap.deleteUser("lisi");

			// 察看用户信息
			ldap.selectUser(user);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
