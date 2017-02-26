package com.boubei.learn.jk.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DBConnectionPool { 
	private static Vector<Connection> pool;

	private final int POOL_MAX_SIZE = 20;

	/*
	 * 获取数据库连接 如果当前池中有可用连接，则将池中最后一个返回，如果没有，则新建一个返回
	 */
	public synchronized Connection getConnection() throws Exception {
		if (pool == null) {
			pool = new Vector<Connection>();
		}
		
		Connection conn;
		if (pool.isEmpty()) {
			conn = createConnection();
		} else {
			int last_idx = pool.size() - 1;
			conn = pool.remove(last_idx);  //分配掉后将该连接从池中remove，下一个用户请求连接的时间给去除后的当前最后一个连接
			                               //每个用户只能单独使用一个连接（如此同步才不会出错）
		}
		return conn;
	}

	/*
	 * 将使用完毕的数据库连接放回备用池。
	 * 
	 * 判断当前池中连接数是否已经超过阀值（POOL_MAX_SIZE）， 如果超过，则关闭该连接。 否则放回池中以备下次重用。
	 */
	public synchronized void releaseConnection(Connection conn) {
		if (pool.size() > POOL_MAX_SIZE) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			pool.add(conn);
		}
	}

	/**
	 * 读取数据库配置信息,并从数据库连接池中获得数据库连接
	 * 
	 * @return
	 * @throws DBException
	 */
	private static Connection createConnection() throws Exception {
		Connection conn;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "personal","personal");
			return conn;
		} catch (ClassNotFoundException e) {
			throw new Exception("ClassNotFoundException when loading JDBC Driver");
		} catch (SQLException e) {
			throw new Exception("SQLException when loading JDBC Driver");
		}
	}
}