package jinhe.lt.database.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/** 
 * <p> PoolManager.java </p> 
 * 
 * @author Jon.King 2006-4-19
 *
 * 充当连接池管理类PoolManager的代理类
 * 
 */
public class PoolManagerProxy {
    private static PoolManager pool = new PoolManager();
    
    /****************获取PoolManagerProxy实例*************/
    private static PoolManagerProxy proxy = null;
    private static final Object lock = new Object();
    
    private PoolManagerProxy() {                        
    }      
    public static PoolManagerProxy getInstance(){
        synchronized (lock) {
            if(proxy == null){
                proxy = new PoolManagerProxy();
            }
            return proxy;
        }
    }
    /***************************************************/

	public ConnectionLocator getConnectionLocatorProxy() {
		PoolManager.ReleasablePoolItem rp = (PoolManager.ReleasablePoolItem) pool.get();
		if (rp == null) {
			return null;
		}
		return new ConnectionLocatorProxy(rp);
	}

	/**
	 * 充当PoolManager.ReleasablePoolItem的代理类,
     * 因为PoolManager.ReleasablePoolItem封装了ConnectionLocator，所以又相当于是ConnectionLocator的代理类
	 * 
	 * The proxy as a nested class:
	 */
	private class ConnectionLocatorProxy implements ConnectionLocator {
		private PoolManager.ReleasablePoolItem implementation;

		public ConnectionLocatorProxy(PoolManager.ReleasablePoolItem rp) {
			implementation = rp;
		}

		public Connection getConnection() {   
            Connection conn = implementation.getConnectionLocator().getConnection();
		    
            ConnectionHandler handler = new ConnectionHandler(this);
            return handler.bind(conn);  //使用handler对conn进行包装,使调用conn.close()时转向releaseConnection()方法
		}

		public void releaseConnection() {
			implementation.release();
		}

        public boolean isClosed() {          
            return implementation.getConnectionLocator().isClosed();
        }
	}
    
    /*
     * 利用getConnection()方法得到的Connection，
     * 程序员很习惯地调用conn.close()方法关闭了数据库连接，那么PoolManager里的池连接机制便形同虚设。
     * 
     * 在调用conn.close()方法时如何自动转向调用releaseConnection()方法？这是关键，用Proxy模式和java反射机制。 
     */
    private class ConnectionHandler implements InvocationHandler {
        private Connection conn;
        private ConnectionLocatorProxy connectionLocatorProxy;
        
        public ConnectionHandler(ConnectionLocatorProxy proxy){
            this.connectionLocatorProxy = proxy;
        }
        
        /**
         * 将动态代理绑定到指定 Connection 对象
         * @param conn
         * @return
         */
        public Connection bind(Connection conn){
           this.conn = conn;
           Connection proxyConn = (Connection)Proxy.newProxyInstance(conn.getClass().getClassLoader(),
                                                                     conn.getClass().getInterfaces(),
                                                                     this);
           return proxyConn;
        }
        
        /** 
         * 拦截conn.close()方法
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           Object obj = null;
           if ("close".equals(method.getName())) {
               connectionLocatorProxy.releaseConnection();
           } else {
               obj = method.invoke(conn, args);
           }
           return obj;
        }
    }
}
