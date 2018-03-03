package com.boubei.learn.yuest.pgsqldb.optimizing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {
	//??
	private JdbcUtils(){
		
	}
	static {
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
}
