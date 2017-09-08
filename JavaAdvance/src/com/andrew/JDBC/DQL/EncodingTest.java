package com.andrew.JDBC.DQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EncodingTest {
	private void selectTest1() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbcdemo?user=root&password=1Csandrew");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM `product`");
        

        while (resultSet.next()) {
            System.out.println(resultSet.getString("productName"));
        }

        resultSet.close();

        statement.close();

        connection.close();
        

    }
	
	public static void main(String[] args) throws Exception {
		
		EncodingTest test = new EncodingTest();
		test.selectTest1();
		
	}
	
	
}
