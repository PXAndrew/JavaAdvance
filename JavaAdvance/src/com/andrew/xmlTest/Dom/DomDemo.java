package com.andrew.xmlTest.Dom;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.InputStream;

public class DomDemo {
    // 文件目录
    private File file = new File(System.getProperty("user.dir") + "/src/com/andrew/xmlTest/Dom/contacts.xml");

    @Test
    public void getDocumentTest() throws Exception {

        // 获取 biulder 工厂类
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 通过工厂类创建 biulder
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 通过 biulder 获取 document（xml） 对象
        Document document = builder.parse(file);

        System.out.println(document);

        System.out.println("document.getElementsByTagName(Name)  : " + document.getElementsByTagName("name").getLength());      // 从整个 xml 找 指定名称的元素

        Element element = document.getDocumentElement();    // 获取文件根节点
        NodeList nodeList = element.getChildNodes();    // 获取根节点的子节点
        NodeList linkmanNodeList = element.getElementsByTagName("linkman");// 获取当前元素下的所有节点的集合

        System.out.println(nodeList.getLength());
        System.out.println(linkmanNodeList.getLength());

        Element element1 = (Element) linkmanNodeList.item(1);       // 获取 第二个 linkman
        System.out.println(element1.getElementsByTagName("name").item(0).getTextContent());

    }

    public void resource() throws Exception {
        // 获取相对路径
//        InputStream inputStream = DomDemo.class.getResourceAsStream("/com/andrew/xmlTest/Dom/contacts.xml");
//        Document document1 = builder.parse(inputStream);
        // 有问题
        ClassLoader classLoader = DomDemo.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("contacts.xml");

//        // 获取 biulder 工厂类
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        // 通过工厂类创建 biulder
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        // 通过 biulder 获取 document（xml） 对象
//        Document document = builder.parse(inputStream);

//        Properties properties = new Properties();
//        properties.load(inputStream);
//        System.out.println(properties.getProperty("username"));

//        System.out.println(document);
    }

    public void modifyEmailTest() throws Exception {

        InputStream inputStream = DomDemo.class.getResourceAsStream("/com/andrew/xmlTest/Dom/contacts.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        Element rootElement = document.getDocumentElement();
        Element emailElement = (Element) rootElement.getElementsByTagName("email").item(0);
        emailElement.setTextContent("bbbbccccb");
        System.out.println(emailElement.getTextContent());


        // 同步
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(file);

        transformer.transform(source, result);
    }

    public void addElementTest() throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        Element rootElement = document.getDocumentElement();

        Element linkManeElement = document.createElement("linkman");
        Element nameElement = document.createElement("name");
        Element emailElement = document.createElement("email");
        Element addressElement = document.createElement("address");
        Element groupElement = document.createElement("group");

        nameElement.setTextContent("zhazhahang");
        emailElement.setTextContent("aaaaaaaa");
        addressElement.setTextContent("fushun");
        groupElement.setTextContent("dongruan");

        linkManeElement.appendChild(nameElement);
        linkManeElement.appendChild(emailElement);
        linkManeElement.appendChild(addressElement);
        linkManeElement.appendChild(groupElement);

        rootElement.appendChild(linkManeElement);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    public void setElementAttrTest() throws Exception {

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        Element rootElement = document.getDocumentElement();

        Element linkman = (Element) rootElement.getElementsByTagName("linkman").item(2);
        linkman.setAttribute("id", "s12345");
        System.out.println(linkman.getAttribute("id"));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    public void deleteElementTest () throws Exception {

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        Element rootElement = document.getDocumentElement();

        Element linkmanElement = (Element) rootElement.getElementsByTagName("linkman").item(2);

        rootElement.removeChild(linkmanElement);    // 删除子节点

        linkmanElement.getParentNode();     // 获取父节点


        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(file));


    }


    public void createDocumentTest() throws Exception {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = null;

        if (file.exists()) {
            document = documentBuilder.parse(file);
        } else {
            document = documentBuilder.newDocument();   // 创建 document
            document.setXmlVersion("1.0");              // 设置 version版本
            Element root = document.createElement("contacts");  // 设置 root 目录
            document.appendChild(root);     // 添加根节点
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(new DOMSource(document), new StreamResult(file));

    }

    public static void main(String[] args) throws Exception {
        DomDemo domDemo = new DomDemo();
//        domDemo.getDocumentTest();
//        domDemo.resource();
//        domDemo.modifyEmailTest();
//        domDemo.addElementTest();
//        domDemo.setElementAttrTest();
//        domDemo.deleteElementTest();
//        System.out.println(System.getProperty("user.dir"));
        domDemo.createDocumentTest();
    }
}
