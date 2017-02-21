package jinhe.lt.ldap.jndi.learnJNDI;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Example shows how to store a an object reference in the directory.
 */

public class ADDReference {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// Set up environment for creating initial context
		Hashtable env = new Hashtable(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:389");

		try {
			// Create the initial context
			Context ctx = new InitialContext(env);

			ctx.bind("apartment=styagi,ou=JavaObjects,o=myserver.com",
					new Apartment("studio", "Mill Complex"));
			ctx.bind("apartment=mojoe,ou=JavaObjects,o=myserver.com",
					new Apartment("2 room", "Farm House Apartments"));
			ctx.bind("apartment=janedoe,ou=JavaObjects,o=myserver.com",
					new Apartment("1 room", "Pond Side"));
			ctx.bind("apartment=rogerp,ou=JavaObjects,o=myserver.com",
					new Apartment("3 room", "Mill Complex"));
			ctx.bind("apartment=jamesm,ou=JavaObjects,o=myserver.com",
					new Apartment("studio", "Fox Hill Apartments"));
			ctx.bind("apartment=paulh,ou=JavaObjects,o=myserver.com",
					new Apartment("duplex", "Woodbridge"));
			ctx.bind("apartment=vkevink,ou=JavaObjects,o=myserver.com",
					new Apartment("1 room", "Woodgate Apartments"));

			Apartment apt = (Apartment) ctx
					.lookup("apartment=styagi,ou=JavaObjects,o=myserver.com");
			System.out.println(apt);

			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			System.out.println("Operation failed: " + e);
		}
	}
}