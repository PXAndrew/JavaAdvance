package com.jdbc.DBDataSource.Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.jdbc.DBDataSource.DBCP.DBCPClass;
import com.jdbc.DBDataSource.DBCP.DBCPUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DruidTest {

    // 连接池 connectionPool
    public static DataSource getDataSource() {
        DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/jdbcdemo");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1Csandrew");
        return basicDataSource;
    }


    public void testQuery() throws Exception {

        String sqlString = "SELECT * FROM product";

        Connection connection = DruidUtil.createConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlString);
        while (resultSet.next()) {

            System.out.println(resultSet.getLong("id") + "\t" + resultSet.getString("productName") + "\t\t" + resultSet.getDouble("salePrice"));
        }

        DruidUtil.closeConnection(connection, statement, resultSet);

    }

    public void dataSourceDBCPTest() {




    }

    public static void main(String[] args) throws Exception {


        DBCPClass dbcpClass = new DBCPClass();
        dbcpClass.testQuery();


    }

}
