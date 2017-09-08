package com.andrew.JunitTest.Junit4;

import org.junit.*;

public class Junit4Demo {
    /*
    * 在你不知道如何做测试之前，就不要去做设计
    *
    * 测试现行（先做测试，再写实现）
    * */
    @BeforeClass
    public static void startClass() {
        System.out.println("静态init");
    }

    @AfterClass
    public static void staticDealloc() {
        System.out.println("静态dealloc");

    }

    @Test
    public void save() {
        System.out.println("save()");
    }

    @Before
    public void init() {
        System.out.println("init()");

    }

    @After
    public void dealloc() {
        System.out.println("dealloc()");
    }
}
