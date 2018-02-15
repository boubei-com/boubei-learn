package com.boubei.learn.jk.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class PostgresSQLTest {
	
	static String url = "jdbc:postgresql://demo.boubei.com:5432/tssdata";
	static String user = "tssx";
	static String pwd = "boubei@com";

	public static void main(String[] args) throws Exception {
		
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		
		st.execute("DROP TABLE IF EXISTS user_tbl");
		
		st.execute("CREATE TABLE user_tbl(name VARCHAR(20), signup_date DATE);");
		st.execute("INSERT INTO user_tbl(name, signup_date) VALUES('张三', '2013-12-22');");
		
		ResultSet rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date") );
		}
		
		st.execute("UPDATE user_tbl set name = '李四' WHERE name = '张三';");
		
		rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date") );
		}
		
		st.execute("ALTER TABLE user_tbl ALTER  signup_date SET NOT NULL");
		st.execute("ALTER TABLE user_tbl RENAME signup_date TO signup");
		st.execute("ALTER TABLE user_tbl ADD email VARCHAR(40)");
		
		st.execute("INSERT INTO user_tbl(name, signup, email) VALUES('王五', '2013-11-11', 'bb@163.com');");
		
		rs = st.executeQuery("SELECT * FROM user_tbl limit 2 offset 1");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup") + "  " + rs.getObject("email") );
		}
		
		ResultSetMetaData md = rs.getMetaData();
		System.out.println(md.getColumnClassName(1));
		System.out.println(md.getColumnDisplaySize(1));
		System.out.println(md.getColumnLabel(1));
		System.out.println(md.getColumnName(1));
		System.out.println(md.getColumnType(1));
		System.out.println(md.getColumnTypeName(1));
		System.out.println(md.getPrecision(1));
		System.out.println(md.getScale(1));
		System.out.println(md.getSchemaName(1));
		System.out.println(md.getTableName(1));
		
		st.execute("ALTER TABLE user_tbl DROP COLUMN email");
		st.execute("DELETE FROM user_tbl WHERE name = '李四'");
	}
	
	public static Connection getConnection() {
		Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
        } 
        catch (Exception e) {
        	e.printStackTrace();
        } 
        return conn;
	}
}
