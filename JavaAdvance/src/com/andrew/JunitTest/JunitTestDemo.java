package com.andrew.JunitTest;


public class JunitTestDemo {

    @MyTest
    public void test1() throws Exception {

        System.out.println("test1");
    }

    @MyTest
    public void test2() throws Exception {
        System.out.println("test2");
    }

    @MyBefore
    public void init() {
        System.out.println("init");
    }

    @MyAfter
    public void dealloc() {
        System.out.println("dealloc");
    }


    public static void main(String[] args) throws Exception {
        JunitTestDemo.class.getMethod("dealloc");
    }


}
