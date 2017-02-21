/* ==================================================================   
 * Created [2006-10-15 19:37:57] by Jon.King 
 * ==================================================================  
 * LT 
 * ================================================================== 
 * Copyright (c) Jon.King, 2006-2007 
 * ================================================================== 
 */

package jinhe.lt.database.datasourcepool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

/**
 * <p>
 * Test.java
 * </p>
 * 
 * @author Jon.King 2006-10-15
 * 
 */
public class Test {
    
    public static void main(String[] args){        
        try{
            Test.test();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String DB_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private static String DB_URL =  "jdbc:oracle:thin:@192.168.1.104:1521:orcl";   
    private static String DB_USER = "tss";
    private static String DB_PASSWORD = "tss";
  
//    private static String DB_DRIVER_NAME = "org.gjt.mm.mysql.Driver";
//    private static String DB_DRIVER_NAME = "jdbc:mysql://localhost:3306/test"; 
//    private static String DB_USER = "root";
//    private static String DB_DRIVER_NAME = "jinpujun";
    
    public static void test() throws NameAlreadyBoundException,
            ClassNotFoundException, IllegalAccessException,
            InstantiationException, SQLException, NameNotFoundException {
        
        String dsName = "DataSource1";
        ConnectionParam param = new ConnectionParam(DB_DRIVER_NAME, DB_URL, DB_USER, DB_PASSWORD);
        DataSourceManager.bind(dsName, param);
        System.out.println("bind datasource ok.");

        DataSource ds = DataSourceManager.lookup(dsName);
        try {
            String sql = "";
            for (int i = 0; i < 30; i++) {
                Connection conn = ds.getConnection();
                try {
                    testSQL(conn, sql);
                } finally {
                    try {
                        conn.close();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceManager.unbind(dsName);
            System.out.println("unbind datasource ok.");
            System.exit(0);
        }

    }

    private static void testSQL(Connection conn, String sql) throws SQLException {
        Statement sm = conn.createStatement();
        ResultSet rs = sm.executeQuery("select * from layout");
        while (rs.next()) {
            System.out.println("id: " + rs.getInt(1) + " name: " + rs.getString(15));
        }             
    }
}
