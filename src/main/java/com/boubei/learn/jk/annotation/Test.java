package com.boubei.learn.jk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在这里建立一个简单的基于annotation测试框架。
 * 首先我们需要一个annotation类型来表示某个方法是一个应该被测试工具运行的测试方法。
 * 
 * @author bl00618
 *
 */

@Documented
// 这个Annotation可以被写入javadoc

@Inherited
// 这个Annotation 可以被继承

@Target( { ElementType.CONSTRUCTOR, ElementType.METHOD })
// 表示这个Annotation只能用于修饰 构造函数和方法的声明

@Retention(RetentionPolicy.RUNTIME)
// 表示这个Annotation存入class且可被Jvm读取，被虚拟机保留使其能够在运行时通过反射被读取。
public @interface Test {

}
