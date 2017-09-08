package com.jdbc.DBDataSource.DBCP;

import com.andrew.smis.util.JdbcUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCPClass {



	// 连接池 connectionPool
    public static DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/jdbcdemo");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1Csandrew");
        return basicDataSource;
    }


    public void testQuery() throws Exception {

        String sqlString = "SELECT * FROM product";

        Connection connection = DBCPUtil.createConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlString);
        while (resultSet.next()) {

            System.out.println(resultSet.getLong("id") + "\t" + resultSet.getString("productName") + "\t\t" + resultSet.getDouble("salePrice"));
        }

        DBCPUtil.closeConnection(connection, statement, resultSet);

    }

    public void dataSourceDBCPTest() {




    }

    public static void main(String[] args) throws Exception {

        DBCPClass dbcpClass = new DBCPClass();
        dbcpClass.testQuery();

    }

}
