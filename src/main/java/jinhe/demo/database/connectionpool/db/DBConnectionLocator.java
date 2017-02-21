package jinhe.lt.database.connectionpool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jinhe.lt.database.connectionpool.ConnectionLocator;
import jinhe.lt.util.BeanUtil;

/** 
 * <p> DBConnection.java </p> 
 * 
 * @author Jon.King 2006-4-24
 *
 * 数据库类型的连接Connetion本地类
 */
public class DBConnectionLocator implements ConnectionLocator {
    private static String DB_DRIVER_NAME = //"oracle.jdbc.driver.OracleDriver";
                                          "org.gjt.mm.mysql.Driver";
                                       // "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    
    private static String DB_URL = //"jdbc:oracle:thin:@localhost:1521:myoracle";
                                 "jdbc:mysql://localhost:3306/test"; 
                                // "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=quickstart";
    
    private static String DB_USER = "root";
                             
    private static String DB_PASSWORD = "jinpujun";
    
    private Connection conn;
   
    public  DBConnectionLocator(){
        try {
               BeanUtil.newInstanceByName(DB_DRIVER_NAME);
               conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch (SQLException e) {
            throw new RuntimeException("创建数据库连接失败！", e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void releaseConnection() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭数据库连接失败！", e);
            }
        }
    }

    public boolean isClosed() {
        boolean isClosed = false;
        try {
            isClosed = (conn == null || conn.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException("判断数据库连接是否关闭！", e);
        }
        return isClosed;
    }
}