package com.andrew.Annotation.Create;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// 被贴的程序元素
// 当注解中有唯一的一个属性，并且属性名为 "value" 时，可不写属性名
@VIP(name = "andrew", age = 25, hobby = {"JAVA", "Object-C"}, sex = Gender.Boy)
class Person {


    @Deprecated
    public void doWork() {


    }


}


// 赋予注解特殊的功能
public class CreateAnnotationDemo {

    public static void main(String[] args) throws Exception {
        // 获取 Person 类注解内信息
        Class clz = Person.class;
//        Annotation anno = clz.getAnnotation(VIP.class);
        // 获取 person 类所有的注解
        Annotation[] annos = clz.getAnnotations();
        for (Annotation anno :
                annos)
            System.out.println(anno);

        // 判断 person 类是否有 vip 的注解
        if (clz.isAnnotationPresent(VIP.class)) {
            // 取出 person 类上的 vip 注解
            Annotation anno = clz.getAnnotation(VIP.class);

            VIP vip = (VIP) anno;
            System.out.println(vip.age());
            System.out.println(vip.hobby());
            System.out.println(vip.sex());

            System.out.println(anno);
        }

        Method method = clz.getMethod("doWork");
        Annotation deAnno =  method.getAnnotation(Deprecated.class);
        System.out.println("-----------------------------");
        System.out.println("deAnno:" + deAnno);






    }


}
