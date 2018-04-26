package com.boubei.learn.jk.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLServerTest {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String url = "jdbc:sqlserver://192.168.0.131:1433;databaseName=test1";
	static String user = "sa";
	static String pwd = "sqlserver.open";

	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		 
		st.execute("IF EXISTS  (SELECT  * FROM dbo.SysObjects WHERE ID = object_id(N'user_tbl') AND OBJECTPROPERTY(ID, 'IsTable') = 1) DROP TABLE  user_tbl");
		
		st.execute("CREATE TABLE user_tbl(name VARCHAR(20), signup_date DATE, age int, weight float);");
		st.execute("INSERT INTO user_tbl(name, signup_date, age, weight) VALUES('张三', '2013-12-22', 12, 60.2);");
		st.execute("ALTER TABLE user_tbl ADD CONSTRAINT name_unique UNIQUE (name)");
		st.execute("ALTER TABLE user_tbl DROP CONSTRAINT name_unique");
		st.execute("CREATE unique INDEX name_index on user_tbl (name)");
		st.execute("DROP INDEX user_tbl.name_index");
		
		ResultSet rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date")  
					+ "  " + rs.getObject("age") + "  " + rs.getObject("weight")  );
		}
		
		st.execute("UPDATE user_tbl set name = '李四' WHERE name = '张三';");
		
		rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup_date")  
					+ "  " + rs.getObject("age") + "  " + rs.getObject("weight")  );
		}
		
		st.execute("ALTER TABLE user_tbl ALTER COLUMN signup_date VARCHAR(20) NOT NULL");
		st.execute("exec sp_rename 'user_tbl.signup_date','signup','COLUMN'");
		st.execute("ALTER TABLE user_tbl ADD email VARCHAR(40)");
		st.execute("ALTER TABLE user_tbl DROP COLUMN email");
		
		st.execute("ALTER TABLE user_tbl ALTER COLUMN weight int");
		
		rs = st.executeQuery("SELECT * FROM user_tbl");
		while(rs.next()) {
			System.out.println( rs.getString("name") + "  " + rs.getObject("signup")  
					+ "  " + rs.getObject("age") + "  " + rs.getObject("weight")  );
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
