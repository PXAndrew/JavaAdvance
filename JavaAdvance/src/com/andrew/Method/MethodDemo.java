package com.andrew.Method;

import java.lang.reflect.Method;

class User {

    public void sayHello() {


    }

    public void sayHey(String name) {

    }

    private String sayHey(String name, int age) {
        return name + "_" + age;
    }

    public void test(String[] names) {


    }

}


public class MethodDemo {

    public static void main(String[] args) throws Exception {
//        getAll();
        getOne();


    }

    public static void getOne() throws Exception {
        Method method = User.class.getMethod("sayHey", String.class);
        Method privateMethod = User.class.getDeclaredMethod("sayHey", String.class, int.class);
        System.out.println(method);
        System.out.println(privateMethod);
        Method privateTestMethod = User.class.getDeclaredMethod("test", String[].class);
        System.out.println(privateTestMethod);

    }

    private static void getAll() {
        Class clz = User.class;
        Method[] methods = clz.getMethods();
        for (Method method :
                methods) {
            System.out.println(method);
        }

        System.out.println("---------------------------------------");
//      获取自身方法，与继承无关
        methods = clz.getDeclaredMethods();
        for (Method method :
                methods) {
            System.out.println(method);
        }


    }

}
