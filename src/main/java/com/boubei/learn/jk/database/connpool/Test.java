package com.boubei.learn.jk.database.connpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * 连接池修改记录

  2006-04-24 
  1.加入了连接池阀值机制(即连接池中连接个数的最大数)
  2.加入了拦截机制,调用conn.close()时会自动转向调用池机制的releaseConnection()方法.
  
  
  进一步修改设想,加入线程机制,使请求连接时如果连接池已满了,自动转入等待状态.
  考虑observer模式,一有连接空下来,马上通知等待的请求线程.
  
 * @author Jon.King
 *
 */
public class Test {
    public static PoolManagerProxy proxy;
    static {
        proxy = PoolManagerProxy.getInstance();
    }

    public static void main(String args[]) {
        ConnectionLocator connectionLocatorProxy = null;
        Connection conn = null;
        
        try {
            //测试连接池阀值POOL_MAX_SIZE = 1000
            for(int i = 0; i < 1001; i++){
                connectionLocatorProxy = proxy.getConnectionLocatorProxy();
                conn = connectionLocatorProxy.getConnection();
            
                Statement sm = conn.createStatement();
                ResultSet rs = sm.executeQuery("select * from cat");
                while (rs.next()) {
                    System.out.println("id: " + rs.getInt(1) + " name: " + rs.getString(2));
                }               
                
                conn.close();//现在 <==> connectionLocatorProxy.releaseConnection();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                conn.close();
                /*
                 * 利用getConnection()方法得到的Connection，
                 * 程序员很习惯地调用conn.close()方法关闭了数据库连接，那么PoolManager里的池连接机制便形同虚设。
                 * 
                 * 在调用conn.close()方法时如何自动转向调用releaseConnection()方法？这是关键，用Proxy模式和java反射机制。 
                 */
            } catch (SQLException e) {
                throw new RuntimeException("数据库连接关闭失败！", e);
            }
        }
    }
}