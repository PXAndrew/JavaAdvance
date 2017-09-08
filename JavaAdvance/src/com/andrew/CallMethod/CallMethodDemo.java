package com.andrew.CallMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

class User {

    public void sayHello() {
        System.out.println("User.sayHello");
    }

    public void sayHey(String name) {
        System.out.println("User.sayHey");
    }

    private String sayHey(String name, int age) {
        return name + "_" + age;
    }

    public void test(String[] names) {


    }

    public static void show1(String name) {
        System.out.println(name);
    }

    public static void show2(String[] name, int test) {
        System.out.println(Arrays.toString(name) + test);
    }
}

public class CallMethodDemo {

    public static void main(String[] args) throws Exception {

        User.class.getDeclaredMethod("sayHello").invoke(User.class.newInstance());
//        System.out.println(sayHey);
        Method m = User.class.getDeclaredMethod("sayHey", String.class, int.class);
        m.setAccessible(true);
        Object obj = m.invoke(User.class.newInstance(), "andrew", 25);
        System.out.println(obj);


        System.out.println("---------------------------------------");

        Object o = new java.util.Date();
        Method method = o.getClass().getMethod("toLocaleString");
        Object stringObj = method.invoke(o);

        System.out.println(stringObj);

        System.out.println("---------------------------------------");

        Method show1Method = User.class.getMethod("show1", String.class);
        show1Method.invoke(null, "andrew");


        Method show2Method = User.class.getMethod("show2", String[].class, int.class);
        show2Method.invoke(null, new Object[]{new String[]{"jiaojian", "andrew"}, 20});

    }
}
