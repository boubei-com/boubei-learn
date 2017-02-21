package jinhe.lt.database.datasourcepool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * _Connection.java
 * </p>
 * 
 * @author 金普俊 2006-10-15
 * 
 * 数据连接的自封装，屏蔽了close方法
 */
class _Connection {
    private final static String CLOSE_METHOD_NAME = "close";

    private Connection conn = null;    
    private boolean inUse = false;  // 数据库的忙状态  
    private long lastAccessTime = System.currentTimeMillis(); // 用户最后一次访问该连接方法的时间

    _Connection(Connection conn, boolean inUse) {
        this.conn = conn;
        this.inUse = inUse;
    }
 
    public Connection getConnection() {
        // 返回数据库连接conn的动态代理的接管类，以便截住close方法
        return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = null;     
                
                // 判断是否调用了close的方法，如果调用close方法则把连接置为无用状态          
                if (CLOSE_METHOD_NAME.equals(method.getName())) 
                    setInUse(false);
                else
                    obj = method.invoke(conn, args); 
                
                lastAccessTime = System.currentTimeMillis(); // 设置最后一次访问时间，以便及时清除超时的连接
                return obj;
            }
        });
    }

    /**
     * 该方法真正的关闭了数据库的连接
     * @throws SQLException
     */
    void close() throws SQLException {
        conn.close(); // 由于类属性conn是没有被接管的连接，因此一旦调用close方法后就直接关闭连接   
    }
 
    public boolean isInUse() {
        return inUse;
    }
 
    public long getLastAccessTime() {
        return lastAccessTime;
    }
 
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
