package jinhe.lt.database.datasourcepool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jinhe.lt.util.BeanUtil;

/**
 * <p>
 * DataSourceImpl.java
 * </p>
 * 
 * @author 金普俊 2006-10-15
 * 
 */
public class DataSourceImpl implements DataSource {
    
    private ConnectionParam connParam;   
    private PrintWriter writer;
    private List<_Connection> connections = new ArrayList<_Connection>();
    private boolean isToStop = false;

    DataSourceImpl(ConnectionParam param) {
        this.connParam = param;
        this.writer = new PrintWriter(System.out, true);
        this.isToStop = false;
    }

    public int getLoginTimeout() throws SQLException {
        return (int) connParam.getWaitTime();
    }

    public void setLoginTimeout(int waitTime) throws SQLException {
        connParam.setWaitTime(waitTime);
    }

    public PrintWriter getLogWriter() throws SQLException {
        return writer;
    }

    public void setLogWriter(PrintWriter writer) throws SQLException {
        this.writer = writer;
    }
    
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

    public Connection getConnection() throws SQLException {
        return getConnection(connParam.getUser(), connParam.getPassword());
    }
    
    public Connection getConnection(String user, String password) throws SQLException {       
        Connection conn = getFreeConnection(0); // 首先从连接池中找出空闲的对象
        if (conn == null) {
            // 判断是否超过最大连接数,如果超过最大连接数则等待一定时间查看是否有空闲连接,否则抛出异常告诉用户无可用连接
            if (getConnectionCount() >= connParam.getMaxConnection())
                conn = getFreeConnection(connParam.getWaitTime());
            else {// 没有超过连接数，重新获取一个数据库的连接            
                _Connection _conn = new _Connection(DriverManager.getConnection(connParam.getUrl(), user, password), true);  // 代理将要返回的连接对象
                synchronized (connections) {
                    connections.add(_conn);
                }
                conn = _conn.getConnection();
            }
        }
        return conn;
    }

    private int getConnectionCount() {
        return connections.size();
    }

    /**
     * 从连接池中取一个空闲的连接
     * 
     * @param nTimeout
     *            如果该参数值为0则没有连接时只是返回一个null 否则的话等待nTimeout毫秒看是否还有空闲连接，如果没有抛出异常
     * @return Connection
     * @throws SQLException
     */
    protected synchronized Connection getFreeConnection(long nTimeout) throws SQLException {
        if(isToStop) {
            throw new SQLException("数据源正在关闭，无法获取连接");
        }
        
        Connection conn = null;
        for(_Connection _conn : connections) {
        	//判断连接是否已超时
            if(System.currentTimeMillis() - _conn.getLastAccessTime() > connParam.getTimeoutValue()) {
            	_conn.setInUse(false);
            }
                
            if (!_conn.isInUse()) {
                conn = _conn.getConnection();
                _conn.setInUse(true);
                break;
            }
        }
        
        if (conn == null && nTimeout > 0) {          
            try {
                Thread.sleep(nTimeout);// 等待nTimeout毫秒以便看是否有空闲连接
            } catch (Exception e) {
            }
            conn = getFreeConnection(0);
            if (conn == null) {
            	 throw new SQLException("没有可用的数据库连接");
            }
        }
        return conn;
    }

    /**
     * 关闭该连接池中的所有数据库连接
     * 
     * @return int 返回被关闭连接的个数
     * @throws SQLException
     */
    public int close() throws SQLException {
        int count = 0;
        SQLException excp = null;        
        for (_Connection _conn : connections) {
            try {
                _conn.close();
                count ++;
                
            }  catch (Exception e) {
                if (e instanceof SQLException) {
                    excp = (SQLException) e;
                }
            }
        }
        if (excp != null) {
        	 throw excp;
        }
        return count;
    }

    public void initConnection() throws SQLException {
        BeanUtil.newInstanceByName(connParam.getDriver());
        for(int i = 0; i < this.connParam.getMinConnection(); i++){
            _Connection _conn = new _Connection(
                    DriverManager.getConnection(connParam.getUrl(), connParam.getUser(), connParam.getPassword()), false);
            
            synchronized (connections) {
                connections.add(_conn);
            }
        }
    }

    public void stop() {
        isToStop = true;
    }

}
