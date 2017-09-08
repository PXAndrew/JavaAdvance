package com.andrew.Annotation;

import java.lang.annotation.Retention;
import java.util.List;


@FunctionalInterface        // 函数式接口  保证函数中只有一个抽象方法
interface AnnotationInterface {
    public void FunctionalMethod();
    // 如果存在第二个方法，则报错
//    public void FunctionalMethod2();
}

public class AnnotationDemo extends java.lang.Object {


    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public void test3() {


    }

    @SuppressWarnings("all")
    public void test4() {


    }



//    Warnings
    // 防止堆污染
    @SafeVarargs
    public static <T> List<T> asList2(T... a) {
        return null;
    }


    // 元注解
    /*
    * @Retention() 表示注解可以在哪一个时期
    * 一共有三个时期，都封装在 RetentionPlicy 枚举类中
    * SOURCE: 该注解在编译时就会被丢弃，不会存在在目标文件中，只会存在在源文件中
    * CLASS: 该注解在运行时就会被丢弃，但是一旦加载进 JVM 中就会被丢弃
    * RUNTIME: 该注解可以存在源文件，字节码，和 JVM 中。
    *
    * 自定义的注解都要使用 RUNTIME
    *
    *
    * */
//    @Retention()


    public static void main(String[] args) {

        new AnnotationDemo().test3();

        System.out.println("aaaa");
    }
}
