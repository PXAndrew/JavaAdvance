package com.andrew.JDBC.DQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JDBCDQL {

	private String name;
	
    // 查询 product 表中有多少条数据

    private void selectTest1() throws Exception {
        Class.forName("com.mysql.jdbc.Driver"); 

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo?user=root&password=1Csandrew&useUnicode=true&characterEncoding=UTF-8");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM `product`");
        

        while (resultSet.next()) {
            System.out.println(resultSet.getString("productName"));
        }

        resultSet.close();

        statement.close();

        connection.close();

    }

    // 迭代器
    public void testIterator() {
        List<String> stringList = Arrays.asList("A", "B", "C", "D");
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) throws Exception {
        JDBCDQL jdbcdql = new JDBCDQL();
//        jdbcdql.testIterator();
        jdbcdql.selectTest1();
    }
}
