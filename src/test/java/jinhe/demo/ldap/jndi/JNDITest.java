package jinhe.lt.ldap.jndi;

import java.io.Serializable;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * @author Jon.King  2006-2-13
 *
 */
@SuppressWarnings("unchecked")
public class JNDITest {
	
	public DirContext getDirContext(){
		Hashtable env = new Hashtable(11);
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");// configure the service provider		
		env.put(Context.PROVIDER_URL, "ldap://192.168.0.6:389");  // the Ldap Server + BaseDn in Url format
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.SECURITY_PRINCIPAL,"cn=admin_hz"); // specify the username
		env.put(Context.SECURITY_CREDENTIALS,"password");           // specify the password

		try {
			return new InitialDirContext(env);			
		} catch (NamingException e) {
			System.err.println("Error: " + e);
		}
		return null;
	}
	
	public void testConnect(){
		try {
			DirContext ctx = getDirContext();
			
			String DN = "CN=经济建设科 仙居财政局,OU=仙居,OU=台州,O=ZJCZ";
			Attributes attrs = ctx.getAttributes(DN);
			for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {
				Attribute attr = (Attribute) ae.next();
				System.out.println("attribute: " + attr.getID());

				for (NamingEnumeration e = attr.getAll(); e.hasMore(); )
					System.out.println("value: " + e.next());
			}
			ctx.close();
		} catch (NamingException e) {
			System.err.println("Error: " + e);
		}
	}
	
	public void testStore(){
		try {
			DirContext ctx = getDirContext();
			MyObject obj = new MyObject();
			ctx.bind("cn=anobject", obj); 
			
//			Perform bind and specify codebase
//			BasicAttribytes battr = new BasicAttributes("javaCodebase","http://myserver.com/classes");
//			ctx.bind("cn=anobject", obj, battr);

		    obj = (MyObject)ctx.lookup("cn=anobject");

			ctx.close();
		} catch (NamingException e) {
			System.err.println("Error: " + e);
		}
	}
	
	public static void main(String[] args) {
		JNDITest test = new JNDITest();
		test.testConnect();
	}
}

class MyObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
}
