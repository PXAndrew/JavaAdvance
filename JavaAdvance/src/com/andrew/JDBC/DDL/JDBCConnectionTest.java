package com.andrew.JDBC.DDL;

import java.sql.*;

public class JDBCConnectionTest {

    private void jdbcConnectionMysqlTest() throws Exception {
//        把 com.mysql.jdbc.Driver 加载进 JVM
//        当 字节码 加载进JVM的时候就会执行 静态代码块
//        而其静态代码块的工作就是完成注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 创建链接对象
        // url = "jdbc:mysql://127.0.0.1:3306/jdbcdemo"
        // 如果 链接是本机的 mysql 端口是3306 则可以简写 url = "jdbc:mysql:///jdbcdemo"
        // mysql 可以通过 show PROCESSLIST 命令来查看当前进程有几个 mysql 进程
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
        // 从JAVA 6开始 就无需获取注册驱动， 直接链接即可
        // 在 web 开发中 还是得保持手动注册
        // PS  有的时候，我们会看到 驱动类是org.gjt.mm.mysql.Driver 的情况。

    }
    // 抛出异常的正确书写方式
    private void jdbcCreateTableTestException() {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
            // 获取 语句对象
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE `s_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20), `age` INT(11))");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void jdbcCreateTableTestExceptionJava7() {
        try(
               Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
        // 获取 语句对象
               Statement statement = connection.createStatement();
                ) {
            statement.executeUpdate("CREATE TABLE `s_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20), `age` INT(11))");
        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }





    private void jdbcCreateTableTest() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");
        // 获取 语句对象
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE `s_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(20), `age` INT(11))");

        statement.close();

        connection.close();

    }







    public static void main(String[] args) throws Exception {
        JDBCConnectionTest jdbcConnectionTest = new JDBCConnectionTest();
//        jdbcConnectionTest.jdbcConnectionMysqlTest();
        jdbcConnectionTest.jdbcCreateTableTest();

//        jdbcConnectionTest.jdbcInsertTableTest();

//        jdbcConnectionTest.jdbcDeleteDataTest();

//        jdbcConnectionTest.jdbcModifyDataTest();

    }

}
