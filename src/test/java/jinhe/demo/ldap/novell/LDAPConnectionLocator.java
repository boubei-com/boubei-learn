package jinhe.lt.ldap.novell;

import com.novell.ldap.LDAPConnection;


public class LDAPConnectionLocator {
    private static LDAPConnectionAdapter adapter = new LDAPConnectionAdapter();

    public static LDAPConnection getConnection() {
        return adapter.getConnection();
    }
    
    public static void releaseConnection() {
        adapter.close();
    }
    
    public static boolean isClosed(){
        return adapter.isClosed();
    }
}
