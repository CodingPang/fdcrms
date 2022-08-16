package com.fdcrms.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 数据库操作的基础工具类
 * Connection
 * PreparedStatement
 * ResultSet
 */

 public class DBUtil {
    // 静态变量：在类加载时执行。
    // 并且是有顺序的。自上而下的顺序。

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3307/fdcrms?useSSL=false&characterEncoding=utf-8&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "123456";
    private static Connection conn = null; // 数据库连接对象
    private static PreparedStatement ps = null; // 预编译对象
    private static ResultSet resultSet = null; // 查询结果集对象

    static {
        // 1、注册驱动(注册驱动只需要注册一次，放在静态代码块当中。DBUtil类加载的时候执行。)
        // "com.mysql.jdbc.Driver" 是连接数据库的驱动，不能写死。因为以后可能还会连接Oracle数据库。
        // 如果连接Oracle数据库的时候，还需要修改Java代码，显然违背了OCP开闭原则。
        // OCP开闭原则：对扩展开放，对修改关闭。(什么是符合OCP呢？在进行功能扩展的时候，不需要修改Java源代码。)
        // Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return conn
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        // 2、获取连接
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

     /**
      * 创建预编译语句对象
      * @param sql 要执行的sql命令
      * @return 执行了预编译之后的命令对象(对象全局化, 此处是否返回都可以)
      */
     public static   PreparedStatement createPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
         ps = getConnection().prepareStatement(sql);
         return ps;
     }

     /**
      * 执行增删改的方法
      * @param preparedStatement 增删改类型的sql语句的执行命令对象
      * @return 影响的行数
      * @throws SQLException
      */
     public static   int execUpdate(PreparedStatement preparedStatement) throws SQLException {
         return preparedStatement.executeUpdate();
     }

     /**
      * 执行查询的方法
      * @param preparedStatement 查询类型的sql语句的执行命令对象
      * @return 返回结果集
      * @throws SQLException
      */
     public static ResultSet execQuery(PreparedStatement preparedStatement) throws SQLException {
         resultSet = preparedStatement.executeQuery();
         return resultSet;
     }

    /**
     * 释放资源
     * @throws SQLException
     */
     public static void close() throws SQLException {
         if (resultSet != null) {
             resultSet.close();
         }
         if (ps != null) {
             ps.close();
         }
         if (conn != null) {
             conn.close();
         }
     }

 }