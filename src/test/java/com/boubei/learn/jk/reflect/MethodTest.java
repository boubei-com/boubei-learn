package com.boubei.learn.jk.reflect;

import java.lang.reflect.Method;

/**
 * <p>
 * MethodTest.java
 * </p>
 * 
 * @author Jon.King 2006-4-19
 * 
 */
public class MethodTest {
 
    public int add(int a, int b) {
        return a + b;
    }
    
    public static void invokeMethod(){
        try {
            Class<?> clazz = Class.forName("jinhe.lt.reflect.MethodTest");
           
            Class<?> partypes[] = new Class[2];
            partypes[0] = Integer.TYPE;
            partypes[1] = Integer.TYPE;
            
            Method method = clazz.getMethod("add", partypes);
            
            MethodTest methodObj = (MethodTest) clazz.newInstance();
            
            Object arglist[] = new Object[2];
            arglist[0] = new Integer(37);
            arglist[1] = new Integer(47);
            
            Object returnObj = method.invoke(methodObj, arglist);//返回值是被调用方法的返回值
            Integer retval = (Integer) returnObj;
            
            System.out.println(retval.intValue());
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
    
    /**
     * 这个程序首先取得 method1 类的描述，然后调用 getDeclaredMethods 
     * 来获取一系列的 Method 对象，它们分别描述了定义在类中的每一个方法，
     * 包括 public 方法、protected 方法、package 方法和 private 方法等。
     * 
     * 如果你在程序中使用 getMethods 来代替 getDeclaredMethods，你还能
     * 获得继承来的各个方法的信息。
     */
    public static void getMethods(){
        try {
            Class<?> cls = Class.forName("jinhe.lt.reflect.MethodTest");
            Method methlist[] = cls.getDeclaredMethods();
            for (int i = 0; i < methlist.length; i++) {
                Method m = methlist[i];
                System.out.println("name = " + m.getName());
                System.out.println("decl class = " + m.getDeclaringClass());
                
                Class<?> pvec[] = m.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                
                Class<?> evec[] = m.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                
                System.out.println("return type = " + m.getReturnType());
                
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }


    public static void main(String args[]) {
        MethodTest.getMethods();
        MethodTest.invokeMethod();
    }
}
