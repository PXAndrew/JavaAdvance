package com.jdbc.blob;

import com.andrew.smis.util.JdbcUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;


public class BlobTest {

    public void write() throws Exception {

        String sqlString = "INSERT INTO image (image) VALUES (?)";

        Connection connection = JdbcUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setBlob(1, new FileInputStream("/Users/ben/Desktop/1.1（1）.jpg"));
        preparedStatement.executeUpdate();
        JdbcUtil.closeConnection(connection, preparedStatement, null);
    }

    public void read() throws Exception {

        String sqlString = "SELECT image FROM image WHERE id = ?";

        Connection connection = JdbcUtil.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Blob imageBlob = resultSet.getBlob("image");
            InputStream inputStream = imageBlob.getBinaryStream();
            // java7 copy
            Files.copy(inputStream, Paths.get("/Users/ben/Desktop/123.jpg"));
        }

        JdbcUtil.closeConnection(connection, preparedStatement, resultSet);

    }

    public static void main (String[] args) throws Exception {
        BlobTest blobTest = new BlobTest();
//        blobTest.write();
        blobTest.read();
    }


}
