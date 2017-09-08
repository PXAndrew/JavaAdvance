package com.andrew.xmlTest.Dom;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;

import java.util.List;

public class Dom4jDemo {

    // 文件目录
    private File file = new File(System.getProperty("user.dir") + "/src/com/andrew/xmlTest/Dom/contacts.xml");
    // 查询所有联系人的信息
    public void selectXMLTest() throws Exception {
        // 获取 document 对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        // 获取根节点
        Element rootElement = document.getRootElement();

        // 获取根元素中所有的 linkman 元素
        List<Element> linkmanList = rootElement.elements("linkman");
        System.out.println(linkmanList.size());

        for (Element element :
                linkmanList) {
            String id = element.attributeValue("id");   // 获取 linkman 元素的 id 属性
            // 获取 linkman 的子元素的文本内容
//            String name = element.elementText("name");
            String name = element.element("name").getText();
        }
    }

    public void addElementTest() throws Exception {

        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element rootElement = document.getRootElement();

        Element linkmanElement = rootElement.addElement("linkman");


        linkmanElement.setAttributeValue("id", "789");
        linkmanElement.addElement("name").setText("jiaojian");
        linkmanElement.addElement("email").setText("aaaa@qq.com");
        linkmanElement.addElement("address").setText("dongruan");
        linkmanElement.addElement("group").setText("LOL");

        // 同步操作
//        FileWriter fileWriter = new FileWriter(file);
//        document.write(fileWriter);
//        fileWriter.close();

        // 同步操作
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();       // 格式化输出
                                                // createCompactFormat      一行
        outputFormat.setEncoding("UTF-8");      // 存储格式
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(file), outputFormat);
        xmlWriter.write(document);
        xmlWriter.close();

    }



    public static void main(String[] args) throws Exception {
        Dom4jDemo dom4jDemo = new Dom4jDemo();
//        dom4jDemo.selectXMLTest();
        dom4jDemo.addElementTest();
    }

}
