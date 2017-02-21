package jinhe.lt.ldap.novell.test;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchConstraints;
import com.novell.ldap.LDAPSearchResults;
import com.novell.ldap.util.Base64;

/**
 * @author jinpj 2006-2-20
 * 
 */
public class NovellLDAPTest {

	public LDAPConnection getLDAPConn() {
		String ldapHost = "192.168.0.88";
		int ldapPort = 390;// LDAPConnection.DEFAULT_PORT;
		int ldapVersion = 3;// LDAPConnection.LDAP_V3;
		String loginDN = "admin";
		String password = "shulicq";

		LDAPConnection lc = new LDAPConnection();

		try {
			lc.connect(ldapHost, ldapPort);
			lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error: " + e.toString());
		}
		return lc;
	}

	public void list() {
		LDAPConnection lc = getLDAPConn();

		int searchScope = LDAPConnection.SCOPE_ONE;
		boolean attributeOnly = true;
		String[] attrs = { LDAPConnection.NO_ATTRS };
		String searchBase = "";
		String searchFilter = null;

		try {
			LDAPSearchResults searchResults = lc.search(searchBase,
					searchScope, searchFilter, attrs, attributeOnly);

			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = null;
				try {
					nextEntry = searchResults.next();
				} catch (LDAPException e) {
					System.out.println("Error: " + e.toString());
					continue;
				}
				System.out.println("\n" + nextEntry.getDN());
			}

			lc.disconnect();
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public void search() {
		LDAPConnection lc = getLDAPConn();

		int searchScope = LDAPConnection.SCOPE_ONE;

		String searchBase = "";
		boolean attributeOnly = false;
		//String[] attrs = { LDAPConnection.NO_ATTRS };
		String searchFilter = null;

		try {
			LDAPSearchConstraints constraints = lc.getSearchConstraints();
			int timelimit = 30;
			int maxresults = 10;
			constraints.setServerTimeLimit(timelimit);
			constraints.setMaxResults(maxresults);

			LDAPSearchResults searchResults = lc.search(searchBase,
					searchScope, searchFilter, null, attributeOnly); // return
			// attrs
			// and
			// values

			/*
			 * To print out the search results, -- The first while loop goes
			 * through all the entries -- The second while loop goes through all
			 * the attributes -- The third while loop goes through all the
			 * attribute values
			 */
			while (searchResults.hasMore()) {
				LDAPEntry nextEntry = searchResults.next();

				System.out.println("\n" + nextEntry.getDN());
				System.out.println("  Attributes: ");

				LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
				Iterator allAttributes = attributeSet.iterator();

				while (allAttributes.hasNext()) {
					LDAPAttribute attribute = (LDAPAttribute) allAttributes
							.next();
					String attributeName = attribute.getName();

					System.out.println("    " + attributeName);

					Enumeration allValues = attribute.getStringValues();

					if (allValues != null) {
						while (allValues.hasMoreElements()) {
							String Value = (String) allValues.nextElement();
							if (Base64.isLDIFSafe(Value)) {
								System.out.println("      " + Value);
							} else {
								// base64 encode and then print out
								Value = Base64.encode(Value.getBytes());
								System.out.println("      " + Value);
							}
						}
					}
				}
			}
			lc.disconnect();
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * 设置新密码
	 * 
	 * @param password
	 * @param ModifyDN
	 * @param NewPassword
	 */
	public void setPassword(String password, String ModifyDN, String NewPassword) {
		LDAPConnection lc = getLDAPConn();

		if (verifyPassword(password, ModifyDN)) {
			return;
		}
		try {
			LDAPAttribute attributePassword = new LDAPAttribute("userPassword",
					NewPassword);
			lc.modify(ModifyDN, new LDAPModification(LDAPModification.REPLACE,
					attributePassword));

			System.out.println("Successfully set the user's password");

			lc.disconnect();
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @param DN
	 */
	public boolean verifyPassword(String password, String DN) {
		LDAPConnection lc = getLDAPConn();
		try {
			LDAPAttribute attr = new LDAPAttribute("userPassword", password);
			boolean correct = lc.compare(DN, attr);

			System.out.println(correct ? "The password is correct."
					: "The password is incorrect.\n");

			lc.disconnect();
			return true;
		} catch (LDAPException e) {
			System.out.println("Error: " + e.toString());
			return false;
		}
	}

	public boolean addUserToGroup(LDAPConnection lc, String userdn, String groupdn) {

		LDAPModification[] modGroup = new LDAPModification[2];
		LDAPModification[] modUser = new LDAPModification[2];

		// Add modifications to modUser

		LDAPAttribute membership = new LDAPAttribute("groupMembership", groupdn);
		modUser[0] = new LDAPModification(LDAPModification.ADD, membership);
		LDAPAttribute security = new LDAPAttribute("securityEquals", groupdn);
		modUser[1] = new LDAPModification(LDAPModification.ADD, security);

		// Add modifications to modGroup

		LDAPAttribute member = new LDAPAttribute("uniqueMember", userdn);
		modGroup[0] = new LDAPModification(LDAPModification.ADD, member);
		LDAPAttribute equivalent = new LDAPAttribute("equivalentToMe", userdn);
		modGroup[1] = new LDAPModification(LDAPModification.ADD, equivalent);

		try {
			// Modify the user's attributes
			lc.modify(userdn, modUser);
			System.out.println("Modified the user's attribute.");
		} catch (LDAPException e) {
			System.out.println("Failed to modify user's attributes: " + e.toString());
			return false;
		}

		try {
			// Modify the group's attributes
			lc.modify(groupdn, modGroup);
			System.out.println("Modified the group's attribute.");
		} catch (LDAPException e) {
			System.out.println("Failed to modify group's attributes: " + e.toString());
			doCleanup(lc, userdn, groupdn);
			return false;
		}
		return true;
	}

	private static void doCleanup(LDAPConnection lc, String userdn, String groupdn) {
		// since we have modified the user's attributes and failed to

		// modify the group's attribute, we need to delete the modified  user's attribute values.

		// modifications for user

		LDAPModification[] modUser = new LDAPModification[2];

		// Delete the groupdn from the user's attributes

		LDAPAttribute membership = new LDAPAttribute("groupMembership", groupdn);
		modUser[0] = new LDAPModification(LDAPModification.DELETE, membership);
		LDAPAttribute security = new LDAPAttribute("securityEquals", groupdn);
		modUser[1] = new LDAPModification(LDAPModification.DELETE, security);

		try {
			// Modify the user's attributes
			lc.modify(userdn, modUser);

			System.out.println("Deleted the modified user's attribute values.");
		} catch (LDAPException e) {
			System.out.println("Could not delete modified user's attributes: "
					+ e.toString());
		}
		return;
	}

	public static void main(String[] args) {
		NovellLDAPTest test = new NovellLDAPTest();
        //test.list();
		test.search();
	}
}
