package com.andrew.JDBC.DML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDMLTest {

    private void jdbcModifyDataTest() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo", "root", "1Csandrew");

        Statement statement = connection.createStatement();

        statement.executeUpdate("UPDATE `s_student` SET `name` = '乔峰', `age` = '34' WHERE `id` = 1");

        statement.close();

        connection.close();

    }

    private void jdbcDeleteDataTest() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo");

        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM `s_student` WHERE `id` = 2");

        statement.close();

        connection.close();

    }

    // 向 t_student 里加入数据（西门吹雪 32）
    private void jdbcInsertTableTest() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo");

        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO `s_student` (name, age) VALUES ('西门吹雪', 32)");

        statement.close();

        connection.close();

    }

    public static void main(String[] args) throws Exception {

        JDBCDMLTest jdbcdmlTest = new JDBCDMLTest();
        jdbcdmlTest.jdbcInsertTableTest();

    }

}
