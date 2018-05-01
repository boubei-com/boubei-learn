package com.boubei.learn.yuest.java.jdbcing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String sql="select * from booktype";
		DBDynamic dtdb=new DBDynamic();
		String DBUrl=dtdb.getDBUrl("mysql","127.0.0.1","test");
	    Connection conn=dtdb.getConn(DBStaticBlock.mysqlDBDriver, DBUrl);
	    PreparedStatement ps=null;
	    ResultSet rs=null;
		try{
		ps=conn.prepareStatement(sql);
		rs=ps.executeQuery();
		if(rs.next()){
			System.out.println(rs.toString());
		}
		}catch(SQLException sqle){
			System.out.println(sqle.toString());
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			dtdb.DBClose(rs, ps, conn);//finally 为什么不能读取try中的变量，难道{}代表一个域？？
		}
		
	}
	

}
