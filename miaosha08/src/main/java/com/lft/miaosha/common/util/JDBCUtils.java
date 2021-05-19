/**
 * Copyright (c) 	2020, laifengting@foxmail.com All Rights Reserved.
 * @Title: JDBCUtils.java
 * @Prject: JDBC_1
 * @Package: com.lft3.util
 * @Description: TODO ADD FUNCTION & REASON.
 * @author: Laifengting
 * @date: 2020年5月13日 下午11:02:13
 * @version: V1.0.0
 */

package com.lft.miaosha.common.util;

/**
 * 操作数据库的工具类
 * @ClassName: JDBCUtils
 * @Description: TODO ADD FUNCTION & REASON.
 * @author: Laifengting
 * @date: 2020年5月13日 下午11:02:13
 * @version 1.0.0
 * @since JDK 13
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    
    /**
     * @return
     * @return Connection
     * @throws Exception
     * @Title: getConnection方法
     * @Description: 获取数据库的连接
     * @author Laifengting
     * @date 2020年5月13日
     * @since JDK 13
     */
    public static Connection getConnection(String url, String driverClass, String userName, String password) throws Exception {
        // 1.加载驱动
        Class.forName(driverClass);
        
        // 2.获取连接
        Connection conn = DriverManager.getConnection(url, userName, password);
        return conn;
    }
    
    /**
     * @param conn
     * @param ps
     * @param rs
     * @return void
     * @Title: closeResource方法
     * @Description: 关闭Connection连接、Statement和ResultSet的操作
     * @author Laifengting
     * @date 2020年5月14日
     * @since JDK 13
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        // 资源的关闭
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param conn
     * @param ps
     * @return void
     * @Title: closeResource方法
     * @Description: 关闭Connection连接和Statement的操作
     * TODO(适用条件/执行流程/使用方法/注意事项)
     * @author Laifengting
     * @date 2020年5月13日
     * @since JDK 13
     */
    public static void closeResource(Connection conn, Statement ps) {
        closeResource(conn, ps, null);
    }
    
    /**
     * @param conn
     * @param ps
     * @return void
     * @Title: closeResource方法
     * @Description: 关闭Connection连接和Statement的操作
     * TODO(适用条件/执行流程/使用方法/注意事项)
     * @author Laifengting
     * @date 2020年5月13日
     * @since JDK 13
     */
    public static void closeResource(Connection conn) {
        closeResource(conn, null, null);
    }
    
    /**
     * @param conn
     * @param ps
     * @return void
     * @Title: closeResource方法
     * @Description: 关闭Connection连接和Statement的操作
     * TODO(适用条件/执行流程/使用方法/注意事项)
     * @author Laifengting
     * @date 2020年5月13日
     * @since JDK 13
     */
    public static void closeResource(Statement ps) {
        closeResource(null, ps, null);
    }
    
    /**
     * @param conn
     * @param ps
     * @param rs
     * @return void
     * @Title: closeResource方法
     * @Description: 关闭Connection连接、Statement和ResultSet的操作
     * @author Laifengting
     * @date 2020年5月14日
     * @since JDK 13
     */
    public static void closeResource(ResultSet rs) {
        closeResource(null, null, rs);
    }
}
