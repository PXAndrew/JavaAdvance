package com.andrew.Annotation.Create;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 定义注解
@Target(ElementType.TYPE) // 只能贴在类、接口、枚举上
@Retention(RetentionPolicy.RUNTIME) // 可以存在源文件，字节码，和JVM中
public @interface VIP {
    // 注解是特殊的接口、接口中有抽象方法，而注解中称抽象方法为属性

    String name();

    //    Integer age(); // 报错： 定义属性类型只能是基础数据类型，或者 String/Class/注解/枚举/以及数组
    int age() default 20;  // 设置默认值

    String[] hobby();

    Gender sex() default Gender.None;

}
