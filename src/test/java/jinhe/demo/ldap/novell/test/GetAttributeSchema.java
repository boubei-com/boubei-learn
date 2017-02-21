package jinhe.lt.ldap.novell.test;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSchema;
import com.novell.ldap.LDAPSearchResults;

public class GetAttributeSchema {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		String ldapHost = "192.168.0.6";
		int ldapPort = 389;// LDAPConnection.DEFAULT_PORT;
		int ldapVersion = 3;// LDAPConnection.LDAP_V3;
		String loginDN = "admin_hz";
		String password = "password";

		int searchScope = LDAPConnection.SCOPE_BASE;
		String entryDN = "CN=应红星 仙居财政局办公室,OU=仙居,OU=台州,O=ZJCZ";
		String searchFilter = "Objectclass=*";
		LDAPConnection lc = new LDAPConnection();

		try {
			lc.connect(ldapHost, ldapPort);

			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));

			LDAPSchema dirSchema = lc.fetchSchema(lc.getSchemaDN());
			
			LDAPSearchResults searchResults = lc.search(entryDN, searchScope,
					searchFilter, null, false);
			LDAPEntry Entry = searchResults.next();
			
			LDAPAttributeSet attributeSet = Entry.getAttributeSet();
			Iterator allAttributes = attributeSet.iterator();

			while (allAttributes.hasNext()) {
				LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
				String attributeName = attribute.getName();

				System.out.println("    " + attribute.getName() + "      "
						+ dirSchema.getAttributeSchema(attributeName) + "\n");
			}
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error: " + e.toString());
		}
	}
}
