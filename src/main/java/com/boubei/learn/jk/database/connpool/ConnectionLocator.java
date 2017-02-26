package com.boubei.learn.jk.database.connpool;

import java.sql.Connection;

/** 
 * <p> Connection.java </p> 
 * 
 * @author Jon.King 2006-4-24
 *
 * 封装Connection的本地类的共同接口，具体实现类可以是LDAPConnectionLocator，DBConnectionLocator等
 * 
 */
public interface ConnectionLocator {
    /**
     * 获的连接
     * @return
     */
    Connection getConnection();

    /**
     * 释放连接
     */
    void releaseConnection();
    
    /**
     * 判断ConnectionLocator中的conn是否已经已经关闭
     * @return
     */
    boolean isClosed();
}


