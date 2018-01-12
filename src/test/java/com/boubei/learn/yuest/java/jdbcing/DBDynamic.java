package com.boubei.learn.yuest.java.jdbcing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBDynamic {
	static Connection conn=null;
	DBDynamic(){
		
	}
	Connection getConn(String DBDriver,String DBUrl) {

		try {
			Class.forName(DBDriver);
			conn = DriverManager.getConnection(DBUrl,
					DBStaticBlock.user, DBStaticBlock.pw);
			return conn;
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.toString());
			return null;
		} catch (SQLException sqle) {
			System.out.println(sqle.toString());
			return null;
		}

	}
	
	//获得连接地址的方法 参数是 数据库类型， 地址，需要连接的库
	String getDBUrl(String dbType,String dbAdress,String dbSchema){
		if(dbType=="mysql"){
			return "jdbc:mysql://"+"dbAdress"+":3306/"+"dbSchema";
		}else if(dbType=="postgresql"){
			return "jdbc:postgresql://"+"dbAdress"+":5432/"+"dbSchema";
		}else if(dbType =="sqlserver"){
			return "jdbc:sqlserver://"+"dbAdress"+":1433/"+"dbSchema";
		}else{
			return null;
		}
	}
	
	//关闭数据库连接的方法
	void DBClose(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
