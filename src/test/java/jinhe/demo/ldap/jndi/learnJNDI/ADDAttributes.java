package jinhe.lt.ldap.jndi.learnJNDI;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * This example shows how to add attributes in the ldap server by adding an
 * object. Creates 7 instances of the user object and stores them in the
 * database
 */
public class ADDAttributes {
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		try {
			// Hashtable for environmental information
			Hashtable env = new Hashtable(11);
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://localhost:389");

			// Get a reference to a directory context
			DirContext ctx = new InitialDirContext(env);

			User p1 = new User("styagi", "Sameer", "Tyagi", "ou=People",
					"styagi@myserver.com");
			User p2 = new User("mojoe", "Moe", "Joe", "ou=People",
					"mojoe@myserver.com");
			User p3 = new User("janedoe", "Jane", "Doe", "ou=People",
					"janedoe@myserver.com");
			User p4 = new User("rogerp", "Roger", "Potter", "ou=People",
					"rogerp@myserver.com");
			User p5 = new User("jamesm", "James", "Manson", "ou=People",
					"jamesm@myserver.com");
			User p6 = new User("paulh", "Paul", "Harding", "ou=People",
					"paulh@myserver.com");
			User p7 = new User("kevink", "Kevin", "Klunk", "ou=People",
					"kevink@myserver.com");

			ctx.bind("uid=styagi,ou=People,o=myserver.com", p1);
			ctx.bind("uid=mojoe,ou=People,o=myserver.com", p2);
			ctx.bind("uid=janedoe,ou=People,o=myserver.com", p3);
			ctx.bind("uid=rogerp,ou=People,o=myserver.com", p4);
			ctx.bind("uid=jamesm,ou=People,o=myserver.com", p5);
			ctx.bind("uid=paulh,ou=People,o=myserver.com", p6);
			ctx.bind("uid=kevink,ou=People,o=myserver.com", p7);
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}
