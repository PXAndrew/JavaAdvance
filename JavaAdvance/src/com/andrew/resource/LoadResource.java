package com.andrew.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadResource {
    
    public static void main(String[] args) throws IOException {
        // 文件名
        String fileName = "db.properties";
        // 属性
        Properties properties = new Properties();
        // 从classpath根目录 找指定的资源文件
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取文件输入流
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        properties.load(inputStream);

        System.out.println(properties);

        System.out.println(properties.getProperty("username"));
        System.out.println(properties.getProperty("password"));

    }

}
