package jinhe.lt.ldap.jndi.learnJNDI;

import java.util.Hashtable;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
  * This example shows how to store a Serializable object to a directory.
  * can be removed with context.unbind() or a destroysubcontext() method
  */

public class ADDSerialize {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {

        // Set up environment for creating initial context
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");

        try {
            // Create the initial context
            Context ctx = new InitialContext(env);

			for(int i=0;i<10;i++){
				String id="vectorid-"+i;
			    Vector vec = new Vector();
			    String cn="cn="+id+",ou=JavaObjects,o=myserver.com";
			    ctx.bind(cn, vec);
			}

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Operation failed: " + e);
        }
    }
}