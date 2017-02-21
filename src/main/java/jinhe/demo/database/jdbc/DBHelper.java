package jinhe.lt.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
//			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			
			String url="jdbc:oracle:thin:@dbserver:1521:forum";
//			String url="jdbc:mysql://localhost:3309/quickstart?user=root&password=jinpujun&useUnicode=true"; 
//			String url="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=jspdev";
			
			conn = DriverManager
					.getConnection(url,"root", "jinpujun");
//			con= DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void releaseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
