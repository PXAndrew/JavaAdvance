package com.andrew.matter;

import com.andrew.smis.util.JdbcUtil;

import javax.xml.transform.Result;
import java.net.CacheRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTest {

    public void transaction() throws Exception {

        String sqlString = "SELECT * FROM account WHERE name = ? AND balance >= ?";
        Connection connection = JdbcUtil.createConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
        prepareStatement.setString(1, "张无忌");
        prepareStatement.setInt(2, 1000);
        ResultSet resultSet =  prepareStatement.executeQuery();
        if (!resultSet.next()) {
            throw new RuntimeException("亲，你的余额不足");
        }
        // 从张无忌的账户中减少1000
        sqlString = "UPDATE account SET balance = balance - ? WHERE name = ?";
        prepareStatement = connection.prepareStatement(sqlString);
        prepareStatement.setInt(1, 1000);
        prepareStatement.setString(2, "张无忌");
        prepareStatement.executeUpdate();

        // 从赵敏的账户增加 1000
        sqlString = "UPDATE  account SET balance = balance + ? WHERE  name = ?";
        prepareStatement = connection.prepareStatement(sqlString);
        prepareStatement.setInt(1, 1000);
        prepareStatement.setString(2, "赵敏");
        prepareStatement.executeUpdate();

        JdbcUtil.closeConnection(connection, prepareStatement, resultSet);
    }


    public void transactionTest() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sqlString = "SELECT * FROM account WHERE name = ? AND balance >= ?";
            connection = JdbcUtil.createConnection();

            // 取消事物自动提交
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, "张无忌");
            preparedStatement.setInt(2, 1000);
            resultSet =  preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("亲，你的余额不足");
            }
            // 从张无忌的账户中减少1000
            sqlString = "UPDATE account SET balance = balance - ? WHERE name = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, 1000);
            preparedStatement.setString(2, "张无忌");
            preparedStatement.executeUpdate();

            // 从赵敏的账户增加 1000
            sqlString = "UPDATE  account SET balance = balance + ? WHERE  name = ?";
            preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, 1000);
            preparedStatement.setString(2, "赵敏");
            preparedStatement.executeUpdate();
            // 提交事物
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, resultSet);
        }
    }



    public static void main(String[] args) throws Exception {
        TransactionTest transactionTest = new TransactionTest();
        transactionTest.transaction();


    }

}
