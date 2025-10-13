package db;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Stack;

public class DBUtil {
    // 为什么要 static
    // final 是干什么的
    private static final String URL = "jdbc:mysql://localhost:3306/students?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // 这里的 static 又是什么，为什么需要
    static {
        try {
            // 这个驱动是固定这么写吗
            // Class 是什么对象
            // forName 方法的作用是什么
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 这是什么写法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 这几个参数都是什么，为什么要不为空时关闭
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}