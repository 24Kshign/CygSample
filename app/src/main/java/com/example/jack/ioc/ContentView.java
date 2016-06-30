package com.example.jack.ioc;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//成员类型是受限制的、合法的类型包括原始类型及String、Class、Annotation，Enumeration

@Target(ElementType.TYPE)
//@Target({ElementType.METHOD,ElementType.TYPE})    //ElementType.METHOD表示作用域是方法，ElementType.TYPE表示作用域是类或者接口
@Retention(RetentionPolicy.RUNTIME)             //ElementType.TYPE表示在所有类型中都能用
public @interface ContentView {

    //如果注解只有一个成员、则成员名必须取名为value()，在使用时可以忽略成员名和赋值号(=)
    int value();
//    int age() default 18     //注解类可以没有成员，没有成员的注解称为标识注解
}
