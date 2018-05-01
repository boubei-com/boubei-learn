package com.boubei.learn.yuest.pgsqldb.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class JdbcInsert {

	/**
	 * @使用JDBC插入数据库一条数据
	 */
	static String url="jdbc:postgresql:///postgres";
	static String usr= "postgres";
	static String pw="postgresql.open";
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection  conn=null;
		Statement st=null;
		try{
		Class.forName("org.postgresql.Driver");
		conn=DriverManager.getConnection(url,usr,pw);
		st=conn.createStatement();
		st.executeUpdate("insert into public.employees values(6,'马跃','杭州市江干区东盛家园',70000.0,29)");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				try{
					if(st!=null){
						st.close();
					};
				}finally{
						if(conn!=null){
							conn.close();
					}
				}
			}
		}	
		
	

}
