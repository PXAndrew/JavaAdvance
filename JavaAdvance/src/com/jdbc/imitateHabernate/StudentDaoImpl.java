package com.jdbc.imitateHabernate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl {

    public static void save(Object object) {

        try {

            // 获取对象对应的表名
            // 获取对应的类名
            String tableString = object.getClass().getSimpleName();
            // 获取标签
            Table table = object.getClass().getAnnotation(Table.class);

            if (table != null) {
                // 如果能获取到标签，则表明为标签的 value 属性
                tableString = table.value();
            }

            // 拼接 sql 语句
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("INSERT INTO ").append(tableString).append("(");
            // 拼接 列明
            StringBuilder columnStringBiulder = new StringBuilder();
            // 拼接 占位符 "？"
            StringBuilder placeHolderBiulder = new StringBuilder();
            // 装参数
            List<Object> paramsList = new ArrayList<>();

            // 获取除了 Object 类中属性之外的 object所有属性
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass(), Object.class);
            // 获取对象中属性名
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor:
                 propertyDescriptors) {
                // 对象中的属性名
                String propertyName = propertyDescriptor.getName();
                if (!"id".equals(propertyName)) {

                    columnStringBiulder.append(propertyName).append(",");
                    placeHolderBiulder.append("?").append(",");

                    // 获取属性的 getter 方法并获取属性值
                    paramsList.add(propertyDescriptor.getReadMethod().invoke(object));
                }
            }

            // 删除拼接字符串后面的 ","
            columnStringBiulder.deleteCharAt(columnStringBiulder.length() - 1);
            placeHolderBiulder.deleteCharAt(placeHolderBiulder.length() - 1);
            // 拼接 SQLString
            sqlStringBuilder.append(columnStringBiulder).append(") VALUES (").append(placeHolderBiulder).append(")");

            System.out.println(sqlStringBuilder);
            // 调用模板
            Template.dmlUpdate(sqlStringBuilder.toString(), paramsList.toArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentDaoImpl.save(new Student("jiaojian", 25));
    }

}
