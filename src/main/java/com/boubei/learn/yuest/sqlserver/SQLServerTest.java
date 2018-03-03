package com.boubei.learn.yuest.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLServerTest {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url = "jdbc:sqlserver://192.168.0.131:1433;databaseName=test";
	static String user = "sa";
	static String pwd = "sqlserver.open";

	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		 
		st.execute("IF EXISTS  (SELECT  * FROM dbo.SysObjects WHERE ID = object_id(N'user_tbl') AND OBJECTPROPERTY(ID, 'IsTable') = 1) DROP TABLE  user_tbl");
		
		st.execute("CREATE TABLE user_tbl(name VARCHAR(20), signup_date DATE);");
		st.execute("INSERT INTO user_tbl(name, signup_date) VALUES('张三', '2013-12-22');");
		st.execute("ALTER TABLE user_tbl ADD CONSTRAINT name_unique UNIQUE (name)");
		st.execute("ALTER TABLE user_tbl DROP CONSTRAINT name_unique");
		//st.execute("INSERT INTO user_tbl(name, signup_date) VALUES('张三', '2013-12-22');");
		st.execute("CREATE unique INDEX name_index on user_tbl (name)");
		st.execute("DROP INDEX user_tbl.name_index");
		
		ResultSet rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date") );
		}
		
		st.execute("UPDATE user_tbl set name = '李四' WHERE name = '张三';");
		
		rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date") );
		}
		
		st.execute("ALTER TABLE user_tbl ALTER  COLUMN signup_date VARCHAR(20) NOT NULL");
		st.execute("exec sp_rename 'user_tbl.signup_date','signup','COLUMN'");
		//st.execute("ALTER TABLE user_tbl RENAME signup_date TO signup");
		st.execute("ALTER TABLE user_tbl ADD email VARCHAR(40)");
		st.execute("ALTER TABLE user_tbl DROP COLUMN email");
		
		rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup") );
		}
		st.execute("DELETE FROM user_tbl WHERE name = '李四'");
	}
	
	public static Connection getConnection() {
		Connection conn = null;
        try {
            Class.forName(driver);
            DriverManager.setLoginTimeout(30);
			conn = DriverManager.getConnection(url, user, pwd);
        } 
        catch (Exception e) {
        	e.printStackTrace();
        } 
        return conn;
	}
}
