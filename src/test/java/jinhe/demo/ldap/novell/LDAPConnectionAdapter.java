package jinhe.lt.ldap.novell;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;

/**
 * @author Jon.King
 * 
 * LDAPConnection适配器
 * 
 */
@SuppressWarnings("unchecked")
public class LDAPConnectionAdapter {
	private static final ThreadLocal session = new ThreadLocal();

	private static final ThreadLocal counter = new ThreadLocal();

	private static final Log log = LogFactory.getLog(LDAPConnectionAdapter.class);

	public LDAPConnectionAdapter() {
		counter.set(new Integer(0));
	}

	/**
	 * 从本地ThreadLocal获取一个连接，为空的话初始化一个
	 * @return
	 */
	public synchronized LDAPConnection getConnection() {
		LDAPConnection lc = (LDAPConnection) session.get();
		if (isClosed()) {
			try {
				lc = initConnection();
				if (lc == null) {
					log.fatal("无法取得LDAP连接!请分别查看LDAP服务器、本地LDAP参数配置及网络!");
					throw new Exception("无法取得LDAP连接!请分别查看LDAP服务器、本地LDAP参数配置及网络!");
				} else {
					session.set(lc);
					counter.set(new Integer(1));
				}
			} catch (Exception e) {
				log.fatal("Can not connect LDAP Server!");
			}
		} else {
			counter.set(new Integer(((Integer) counter.get()).intValue() + 1));
		}
		System.out.println("Connection counter:" + counter.get());
		return lc;
	}

	/**
	 * 初始化一个LDAPConnection，并绑定到directory服务器
	 * @return
	 * @throws LDAPException
	 */
	private LDAPConnection initConnection() throws LDAPException {
		LDAPConnection lc = new LDAPConnection();
		lc.connect(LDAPConstants.getLdapHost(), LDAPConstants.getLdapPort());
		lc.bind(LDAPConstants.getLdapVersion(), LDAPConstants.getLdapAdmindn(),
				LDAPConstants.getLdapPasswd().getBytes());
		return lc;
	}

	/**
	 * 关闭连接，如果当前只有一个客户端连接的话，则将存储LDAPConnection的ThreadLocal置空
	 */
	public synchronized void close() {
		counter.set(new Integer(((Integer) counter.get()).intValue() - 1));
		if (((Integer) counter.get()).intValue() < 1) {
			LDAPConnection lc = (LDAPConnection) session.get();
			if (lc != null) {
				try {
					lc.disconnect();
				} catch (LDAPException e) {
					log.fatal("Can not disconnect LDAP Server!");
				}
			}
			session.set(null);
			counter.set(new Integer(0));
		}
	}

	/**
	 * 判断连接是否已经关闭
	 * @return
	 */
	public synchronized boolean isClosed() {
		LDAPConnection lc = (LDAPConnection) session.get();
		return (lc == null || lc.isConnectionAlive() == false) ? true : false;
	}
}
