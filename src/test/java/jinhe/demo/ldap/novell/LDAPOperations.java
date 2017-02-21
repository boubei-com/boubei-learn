package jinhe.lt.ldap.novell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPObjectClassSchema;
import com.novell.ldap.LDAPSchema;
import com.novell.ldap.LDAPSearchResults;

/**
 * @author Jon.King
 */
@SuppressWarnings("unchecked")
public class LDAPOperations {
	private static final Log log = LogFactory.getLog(LDAPOperations.class);

	public static final String DELIMIT = "|";   //分隔符

	/**
	 * 根据entry的dn获取该条目，找不到的返回null
	 * @param dn
	 * @return LDAPEntry
	 */
	public static LDAPEntry getEntry(String dn) {
		LDAPEntry[] entries = getEntries(dn, "(objectClass=*)", LDAPConnection.SCOPE_BASE);
		if (entries == null || entries.length == 0)
			return null;
		else
			return entries[0];
	}

	/**
	 * 根据basedn,filter,scope查询entries
	 * @param basedn
	 * @param filter
	 * @param scope
	 * @return LDAPEntry[]
	 */
	public static LDAPEntry[] getEntries(String basedn, String filter, int scope) {
		List entries = new ArrayList();
		if (basedn == null || basedn.length() == 0)
			basedn = LDAPConstants.getLdapBasedn();
		if (filter == null)
			filter = "(objectClass=*)";

		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		String[] attrs = { LDAPConnection.ALL_USER_ATTRS };
		LDAPSearchResults searchResults = null;
		try {
			searchResults = lc.search(basedn, scope, filter, attrs, false);
			if (searchResults != null)
				while (searchResults.hasMore()) {
					LDAPEntry entry = searchResults.next();
					entries.add(entry);
				}
		} catch (Exception e) {
			 log.info("LDAP getEntries Failed!"+"basedn="+basedn+"|filter="+filter+"|scope="+scope);
		}
		LDAPEntry[] results = new LDAPEntry[entries.size()];
		entries.toArray(results);

		LDAPConnectionLocator.releaseConnection();
		return results;
	}

	/**
	 * 根据entry的dn获取该条目的所有属性
	 * @param dn
	 * @return Map
	 */
	public static Map getAttributes(String dn) {
		LDAPEntry entry = getEntry(dn);
		return LDAPOperations.transEntry2Attrs(dn, entry);
	}

	/**
	 * 修改一个条目
	 * @param entry
	 * @return
	 */
	public static boolean update(LDAPEntry entry) {
		if (entry == null)
			return false;
		String dn = entry.getDN();
		return update(dn, LDAPOperations.transEntry2Attrs(entry));
	}
	
	/**
	 * 根据属性修改一个条目
	 * @param entry
	 * @return
	 */
	public static boolean update(String dn, Map attributes) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		boolean isOK = false;
		Iterator ikeyset = attributes.keySet().iterator();
		List modList = new ArrayList();
		while (ikeyset.hasNext()) {
			String attrName = (String) ikeyset.next();
			if (attrName.equalsIgnoreCase("objectClass")
					|| attrName.equalsIgnoreCase("objectclass")
					|| attrName.equalsIgnoreCase("ou")
					|| attrName.equalsIgnoreCase("cn")
					|| attrName.equalsIgnoreCase("OU")
					|| attrName.equalsIgnoreCase("CN")) // 这些属性不是这样update的
				continue;
			Object attrValue = attributes.get(attrName);

			LDAPAttribute attribute = buildLDAPAttribute(attrName, attrValue);
			if (attribute != null)
				modList.add(new LDAPModification(LDAPModification.REPLACE, attribute));
		}
		try {
			LDAPModification[] mods = new LDAPModification[modList.size()];
			mods = (LDAPModification[]) modList.toArray(mods);
			lc.modify(dn, mods);
			log.info(dn + "  update successfully.");
			isOK = true;
		} catch (LDAPException e) {
			log.info(dn + " update error!");
		}

		LDAPConnectionLocator.releaseConnection();
		return isOK;
	}

	/**
	 * 将attrValue绑定到对应的attrName，attrValue为空的话返回null
	 * @param attrName
	 * @param attrValue
	 * @return LDAPAttribute
	 */
	private static LDAPAttribute buildLDAPAttribute(String attrName, Object attrValue) {
		String[] values = toStringArray(attrValue);
		boolean decode = false;
		LDAPAttribute attribute = new LDAPAttribute(attrName);
		for (int i = 0, n = values.length; i < n; i++) {
			if (values[i] != null && values[i].length() > 0) {
				attribute.addValue(values[i]);
				decode = true;
			}
		}
		if (decode)
			return attribute;
		else
			return null;
	}

	/**
	 * 往DIT新增一个条目
	 * @param dn
	 * @param attributes
	 * @return 
	 */
	public static boolean insert(String dn, Map attributes) {
		LDAPAttributeSet attributeSet = new LDAPAttributeSet();
		LDAPEntry entry = new LDAPEntry(dn, attributeSet);
		Iterator iter = attributes.keySet().iterator();
		while (iter.hasNext()) {
			String attrName = (String) iter.next();
			Object attrValue = attributes.get(attrName);

			LDAPAttribute attribute = buildLDAPAttribute(attrName, attrValue);
			if (attribute != null)
				attributeSet.add(attribute);

		}
		return insert(entry);
	}

	/**
	 * 将各种形式的值转化为String数组，空的话返回一个空数组
	 * @param o
	 * @return
	 */
	private static String[] toStringArray(Object o) {
		if (o == null)
			return new String[0];
		if (o instanceof String)
			return StringUtils.split((String) o, LDAPOperations.DELIMIT);
		if (o instanceof String[])
			return (String[]) o;
		if (o instanceof List) {
			String[] sArray = new String[((List) o).size()];
			((List) o).toArray(sArray);
			return sArray;
		}
		return new String[0];
	}

	/**
	 * 新增一个条目
	 * @param entry
	 * @return 是否成功
	 */
	public static boolean insert(LDAPEntry entry) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		boolean isOK = false;
		try {
			lc.add(entry);
			isOK = true;
		} catch (LDAPException e) {
			log.info("LDAP Insert Failed!");
			log.fatal(e);
		}
		LDAPConnectionLocator.releaseConnection();
		return isOK;
	}

	/**
	 * 删除节点及其所有子节点
	 * @param dn
	 * @return
	 */
	public static boolean delete(String dn) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		boolean isOK = delete(dn, lc);
		LDAPConnectionLocator.releaseConnection();
		return isOK;
	}

	private static boolean deleteSelf(String dn, LDAPConnection lc) {
		try {
			lc.delete(dn);
			return true;
		} catch (LDAPException e) {
			log.error("LDAP Delete dn='" + dn + "'Failed!\n" + e);
			return false;
		}
	}

	private static boolean delete(String pdn, LDAPConnection lc) {
		boolean isOK = true;
		LDAPEntry[] entries = getEntries(pdn, "(objectClass=*)", LDAPConnection.SCOPE_ONE);
		if (entries != null || entries.length > 0) {
			for (int i = 0; i < entries.length; i++) {
				String dn = entries[i].getDN();
				if (delete(dn, lc) == false)
					isOK = false;
			}
		}
		if (deleteSelf(pdn, lc) == false)
			isOK = false;
		return isOK;
	}
	
	/**
	 * 根据objectClass名称获取schema条目的必要属性
	 * 
	 * @param ocName
	 * @return
	 */
	public static String[] getOCRequiredAttributes(String ocName) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		String[] reqAttrs = null;
		try {
			LDAPSchema schema = lc.fetchSchema(lc.getSchemaDN());
			LDAPObjectClassSchema ocSchema = schema
					.getObjectClassSchema(ocName);
			reqAttrs = ocSchema.getRequiredAttributes();
		} catch (Exception e) {
			log.fatal(e);
		}
		LDAPConnectionLocator.releaseConnection();
		return reqAttrs;
	}

	public static String[] getOCRequiredAttributes(LDAPConnection lc,
			String ocName) {
		String[] reqAttrs = null;
		try {
			LDAPSchema schema = lc.fetchSchema(lc.getSchemaDN());
			LDAPObjectClassSchema ocSchema = schema
					.getObjectClassSchema(ocName);
			reqAttrs = ocSchema.getRequiredAttributes();
		} catch (Exception e) {
			log.fatal(e);
		}
		return reqAttrs;
	}

	/**
	 * 根据objectClass名称获取schema条目的可选属性
	 * 
	 * @param ocName
	 * @return
	 */
	public static String[] getOCOptionalAttributes(String ocName) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		String[] optAttrs = null;
		try {
			LDAPSchema schema = lc.fetchSchema(lc.getSchemaDN());
			LDAPObjectClassSchema ocSchema = schema
					.getObjectClassSchema(ocName);
			optAttrs = ocSchema.getOptionalAttributes();
		} catch (Exception e) {
			log.fatal(e);
		}
		LDAPConnectionLocator.releaseConnection();
		return optAttrs;
	}

	public static String[] getOCOptionalAttributes(LDAPConnection lc,
			String ocName) {
		String[] optAttrs = null;
		try {
			LDAPSchema schema = lc.fetchSchema(lc.getSchemaDN());
			LDAPObjectClassSchema ocSchema = schema
					.getObjectClassSchema(ocName);
			optAttrs = ocSchema.getOptionalAttributes();
		} catch (Exception e) {
			log.fatal(e);
		}
		return optAttrs;
	}

	/**
	 * 将条目的属性取出存放到map中
	 * @param dn
	 * @param entry
	 * @return
	 */
	public static Map transEntry2Attrs(String dn, LDAPEntry entry) {
		Map m = transEntry2Attrs(entry);
		m.put("dn", dn);
		return m;
	}

	public static Map transEntry2Attrs(LDAPEntry entry) {
		Map attrMap = new HashMap();
		if (entry == null){
			return attrMap;
		}			
		LDAPAttributeSet attributeSet = entry.getAttributeSet();
		attrMap.put("dn", entry.getDN());
		
		for (Iterator it = attributeSet.iterator(); it.hasNext();) {
			LDAPAttribute attribute = (LDAPAttribute) it.next();
			attrMap.put(attribute.getName(), sArray2String(attribute.getStringValueArray()));
		}
		return attrMap;
	}

	/**
	 * 将String型的数组转换为用"|"连接起来的字符串
	 * 
	 * @param values
	 * @return
	 */
	private static String sArray2String(String[] values) {
		if (values == null || values.length == 0)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			sb.append(values[i] + "|");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 把LDAP Entry从oldDN移动到newDN(newRDN+newPDN);只移动无子结点的Entry。
	 * 
	 * @param oldDN
	 *            原来的DN
	 * @param newRDN
	 *            新自身DN
	 * @param newPDN
	 *            新父DN
	 * @param delOld
	 *            删除旧的LDAP Entry
	 */
	public static boolean move(String oldDN, String newRDN, String newPDN, boolean delOld) {
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		boolean isOK = false;
		try {
			lc.rename(oldDN, newRDN, newPDN, delOld);
			isOK = true;
		} catch (Exception e) {
			log.error("移到LDAP Entry 出错!\n" + e);
		}
		LDAPConnectionLocator.releaseConnection();
		return isOK;
	}

	/**
	 * 修改密码
	 * @param dn
	 * @param passwd
	 * @return
	 */
	public static boolean verifyPasswd(String dn, String passwd) {
		boolean correct = false;
		if (passwd == null)
			return correct;
		LDAPConnection lc = LDAPConnectionLocator.getConnection();
		try {
			LDAPAttribute attr = new LDAPAttribute("userPassword", passwd);
			correct = lc.compare(dn, attr);
		} catch (Exception e) {
			log.error(e);
		}
		LDAPConnectionLocator.releaseConnection();
		return correct;
	}
}
