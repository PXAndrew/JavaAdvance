package com.jdbc.Template;

import com.andrew.smis.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Template {

    public static void dmlUpdate(String sqlString, Object... params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = com.andrew.smis.util.JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement(sqlString);
            for (int index = 0; index < params.length; index++){
                preparedStatement.setObject(index + 1, params[index]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeConnection(connection, preparedStatement, null);
        }
    }

    public static <T> T dqlQuery(String sqlString, IResultsetHandler<T> resultsetHandler, Object... params) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = com.jdbc.Template.JdbcUtil.createConnection();
            preparedStatement = connection.prepareStatement(sqlString);
            for (int index = 0; index < params.length; index ++) {
                preparedStatement.setObject(index + 1, params[index]);
            }
            resultSet = preparedStatement.executeQuery();
            return resultsetHandler.handle(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.jdbc.Template.JdbcUtil.closeConnection(connection, preparedStatement, resultSet);
        }
        return null;

    }

}
