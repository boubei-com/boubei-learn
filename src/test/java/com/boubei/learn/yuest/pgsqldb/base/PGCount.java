package com.boubei.learn.yuest.pgsqldb.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PGCount {

	/**
	 * @param args
	 */
		private static String driver = "org.postgresql.Driver";
		private static String url="jdbc:postgresql://localhost:5432/postgres";
		private static String usr="postgres";
		private static String password="postgres.open";
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	
	static void getConnection(String driver,Connection conn){
		
		Statement st=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}

}
