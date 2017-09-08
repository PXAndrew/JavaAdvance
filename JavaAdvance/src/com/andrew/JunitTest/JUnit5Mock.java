package com.andrew.JunitTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JUnit5Mock {

    public static void main(String[] args) throws Exception {

        Class clz = JunitTestDemo.class;
        Method[] methods = clz.getMethods();

        List<Method> testList = new ArrayList<>();
        List<Method> afterList = new ArrayList<>();
        List<Method> beforeList = new ArrayList<>();

        for (Method method :
                methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                testList.add(method);
            } else if (method.isAnnotationPresent(MyBefore.class)) {
                beforeList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                afterList.add(method);
            }
        }

        Object obj = clz.newInstance();

        for (Method testMethod :
                testList) {
            for (Method beforeMethod :
                    beforeList) {
                beforeMethod.invoke(obj);
            }
            testMethod.invoke(obj);

            for (Method afterMethod :
                    afterList) {
                afterMethod.invoke(obj);
            }
        }
    }
}
