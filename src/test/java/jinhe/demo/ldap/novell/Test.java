package jinhe.lt.ldap.novell;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
 
@SuppressWarnings("unchecked")
public class Test {
	public static void testGetEntries(){
		int searchScope = LDAPConnection.SCOPE_ONE;
		String searchBase = "OU=仙居,OU=台州,O=ZJCZ";
		String searchFilter = "Objectclass=*";
		
		LDAPEntry[] temp = LDAPOperations.getEntries(searchBase, searchFilter, searchScope);
		for(int i = 0;i < temp.length; i++){
			System.out.println(temp[i]);
		}
	}
	
	public static void testGetEntry(){		
		String dn = "OU=仙居,OU=台州,O=ZJCZ";

		System.out.println(LDAPOperations.getEntry(dn));
	}
	

	public static void testGetOCRequiredAttributes() {
		List list = LDAPConstants.getOpOCS(); //用户所有对象类
		
		Set attrNames = new HashSet();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String ocName = (String) iter.next();
			String attrName[] = LDAPOperations.getOCRequiredAttributes(ocName);
			attrNames.addAll(Arrays.asList(attrName));
			
			attrName = LDAPOperations.getOCOptionalAttributes(ocName);
			if (attrName != null){
				attrNames.addAll(Arrays.asList(attrName));
			}		
		}
		for (Iterator iter = attrNames.iterator(); iter.hasNext();) {
			System.out.println((String) iter.next());
		}
	}
	
	public static void main(String[] args) {
		testGetEntries();
		//testGetEntry();
		//testGetOCRequiredAttributes();
	}
}
