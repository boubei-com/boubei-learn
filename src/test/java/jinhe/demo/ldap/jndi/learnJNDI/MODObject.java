package jinhe.lt.ldap.jndi.learnJNDI;

import java.util.Hashtable;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
  * This example shows how to modify existing objects in the database.
  * Looks up the vectorid-1 object stored from ADDSeralize.java and adds the lastlogin attribute.
  * The lastlogin attribute has multiple values of data,last deposit,last withdrawal,and balance.
  */
public class MODObject {
   // initial context implementation
    @SuppressWarnings("unchecked")
	public static void main(String args[]) {
      try {

       //Hashtable for environmental information
    	Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");

         //Get a reference to a directory context
         DirContext ctx = new InitialDirContext(env);

   		 Attributes myAttrs = new BasicAttributes(true);
		 Attribute oc = new BasicAttribute("lastlogin");
		   		oc.add("Monday January 12 1999"); //date
		   		oc.add("200"); //last deposit
		   		oc.add("43"); //last withdrawal
		   		oc.add("7652"); //balance
		 myAttrs.put(oc);
		 ctx.modifyAttributes("cn=vectorid-1,ou=JavaObjects,o=myserver.com",DirContext.ADD_ATTRIBUTE,myAttrs);

		 Vector  vec= (Vector)ctx.lookup("cn=vectorid-1,ou=JavaObjects,o=myserver.com");

		 // just for testing , see if a vector is returned or not !
		 System.out.println(vec instanceof Vector);


		 /*

		 Alternatively the following piece of code can be used to handle multiple attributes

		 ModificationItem[] mods = new ModificationItem[3];
         Attribute mod0 = new BasicAttribute("lastlogin","Monday January 12 1999");
         Attribute mod1 = new BasicAttribute("ipaddress", "172.54.15.1");
		 Attribute mod2 = new BasicAttribute("location");
         mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,mod0);
         mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE,mod1);
         mods[2] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,mod2);
         ctx.modifyAttributes("cn=myappuser-1,ou=JavaObjects,o=myserver.com", mods);
         */
         ctx.close();

      }
      catch(Exception e){
         e.printStackTrace();

      }


   }
}
