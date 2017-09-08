package com.jdbc.primaryKey;

import com.andrew.smis.util.JdbcUtil;

import java.sql.*;

public class PrimaryKeyTest {

    // 存储数据的同事获取存储数据的 PrimaryKey
    public void statementTest() throws Exception {
        String sqlString = "INSERT INTO s_student (name, age) VALUES ('东方姑娘', 34)";

        Connection connection = JdbcUtil.createConnection();
        Statement statement = connection.createStatement();

        // 返回的是列数
        int row = statement.executeUpdate(sqlString, Statement.RETURN_GENERATED_KEYS);
        // 获取这列的主键
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            Long id = resultSet.getLong(1);
            System.out.println(id);
        }

        JdbcUtil.closeConnection(connection, statement, resultSet);
    }

    public void preparedStatementTest() throws SQLException {

        String sqlString = "INSERT INTO s_student (name, age) VALUES ('jiaojian', 25)";
        Connection connection = JdbcUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
        int row = preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            Long id = resultSet.getLong(1);
            System.out.println(id);

        }

        JdbcUtil.closeConnection(connection, preparedStatement, null);

    }

    public static void main(String[] args) throws Exception {

        PrimaryKeyTest primaryKeyTest = new PrimaryKeyTest();
//        primaryKeyTest.statementTest();
        primaryKeyTest.preparedStatementTest();

    }

}
