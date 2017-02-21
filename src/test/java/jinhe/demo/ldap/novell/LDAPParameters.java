package jinhe.lt.ldap.novell;

import java.util.List;

@SuppressWarnings("unchecked")
public class LDAPParameters {
    private String ldapBasedn;
    private String ldapHost;
    private int ldapPort;
    private String ldapAdmindn;
    private String ldapPasswd;
    private int ldapVersion;
    private List opOCS;         //用户所有对象类
    private List ouOCS;         //部门所有对象类
    
    private String opDnPrefix;  //用户DN的前缀（如：cn）
    private String ouDnPrefix;  //部门DN的前缀（如：ou）
    
    public LDAPParameters(){        
    }

	public String getLdapBasedn() {
		return ldapBasedn;
	}

	public void setLdapBasedn(String ldapBasedn) {
		this.ldapBasedn = ldapBasedn;
	}

	public String getLdapHost() {
		return ldapHost;
	}

	public void setLdapHost(String ldapHost) {
		this.ldapHost = ldapHost;
	}

	public int getLdapPort() {
		return ldapPort;
	}

	public void setLdapPort(int ldapPort) {
		this.ldapPort = ldapPort;
	}

	public String getLdapAdmindn() {
		return ldapAdmindn;
	}

	public void setLdapAdmindn(String ldapAdmindn) {
		this.ldapAdmindn = ldapAdmindn;
	}

	public String getLdapPasswd() {
		return ldapPasswd;
	}

	public void setLdapPasswd(String ldapPasswd) {
		this.ldapPasswd = ldapPasswd;
	}

	public int getLdapVersion() {
		return ldapVersion;
	}

	public void setLdapVersion(int ldapVersion) {
		this.ldapVersion = ldapVersion;
	}

	public List getOpOCS() {
		return opOCS;
	}

	public void setOpOCS(List opOCS) {
		this.opOCS = opOCS;
	}

	public List getOuOCS() {
		return ouOCS;
	}

	public void setOuOCS(List ouOCS) {
		this.ouOCS = ouOCS;
	}

	public String getOpDnPrefix() {
		return opDnPrefix;
	}

	public void setOpDnPrefix(String opDnPrefix) {
		this.opDnPrefix = opDnPrefix;
	}

	public String getOuDnPrefix() {
		return ouDnPrefix;
	}

	public void setOuDnPrefix(String ouDnPrefix) {
		this.ouDnPrefix = ouDnPrefix;
	}
  
}
