package com.boubei.learn.yuest.pgsqldb.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDelete {

	/**
	 * 使用JDBC修改数据库中的一条数据
	 */
	static String url="jdbc:postgresql:///postgres";
	static String usr="postgres";
	static String pw="postgresql.open";
	
	
	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		try{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection(url,usr,pw);//犯错 createConnection（）
			st=conn.createStatement();
			st.executeUpdate("delete  from public.employees where id=6");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(st!=null){
					st.close();
				}
			}finally{
				if(conn!=null){
					conn.close();
				}
			}
		}
		
	}

}
