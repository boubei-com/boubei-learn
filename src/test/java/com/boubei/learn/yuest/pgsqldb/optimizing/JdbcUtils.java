package com.boubei.learn.yuest.pgsqldb.optimizing;


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
