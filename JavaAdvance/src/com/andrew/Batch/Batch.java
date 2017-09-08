package com.andrew.Batch;

import com.andrew.smis.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 批量处理操作
 *
 * @author Andrew
 */
public class Batch {

    /**
     * mysql 没有这个批量处理功能
     * @throws Exception
     */
    private void statementBatch() throws Exception {

        Connection connection = JdbcUtil.createConnection();
        Statement statement = connection.createStatement();

        for (int i = 0; i < 1000; i++) {
            statement.addBatch("INSERT INTO s_student(age) VALUES(" + i + ")");
            if (i % 200 == 0) {
                statement.executeBatch();
                statement.clearBatch();
            }
        }
        JdbcUtil.closeConnection(connection, statement, null);
    }

    private void preparedStatementBatch() throws Exception {

        String sqlString = "INSERT INTO s_student (name, age) VALUES (?, ?)";

        Connection connection = JdbcUtil.createConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);

        for (int i = 0; i < 1000; i++) {

            preparedStatement.setString(1, "andrew");
            preparedStatement.setInt(2, i);
            preparedStatement.addBatch();
            if (i % 200 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();         // 清除批量处理缓存
                preparedStatement.clearParameters();    // 清除参数缓存

            }
        }
        JdbcUtil.closeConnection(connection, preparedStatement, null);
    }


    public static void main(String[] args) throws Exception {

        Batch batch = new Batch();

//        batch.statementBatch();
        batch.preparedStatementBatch();

    }


}
