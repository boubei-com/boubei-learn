package com.boubei.learn.jk.annotation.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/** 
 * <p> ProxyInvocationHandler.java </p> 
 * InvocationHandler基类
 * 
 * @author bl00618
 */
public abstract class ProxyInvocationHandler implements InvocationHandler{
    
    private Object target;  //拦截的对象
    private String[] invokeMethods; //需要拦截的方法，为空则拦截任何方法
   
    /**
     * @param target  拦截的对象
     * @param invokeMethods  需要拦截的方法
     */
    public ProxyInvocationHandler(Object target, String[] invokeMethods){
        this.invokeMethods = invokeMethods;
        this.target = target;
    }
    
    public ProxyInvocationHandler(Object target){
        this.target = target;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果没有指定拦截的方法列表，则拦截接口中所有方法。
        boolean invoke = (invokeMethods == null 
                || (invokeMethods.length > 0 && Arrays.asList(invokeMethods).contains(method.getName())));
        Object returnVal = null;
        Object beforeReturnVal = null;
        try {
            if(invoke){
                //执行拦截前操作
                beforeReturnVal = (Long) before(target, method, args);
            }
            // 执行方法，并获取返回值
            returnVal = method.invoke(target, args);
            return returnVal;
        }catch (InvocationTargetException e) {
            throw e.getTargetException();
        }catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }finally{
            if(invoke){
                //执行拦截后操作
                after(target, method, args, beforeReturnVal, returnVal);
            }
        }
    }

    /**
     * 定义方法执行前的操作
     * @param target
     * @param method
     * @param args
     * @return
     */
    protected abstract Object before(Object target, Method method, Object[] args);
    /**
     * 定义方法执行后的操作
     * @param target
     * @param method
     * @param args
     * @param beforeReturnVal
     */
    protected abstract void after(Object target, Method method, Object[] args, Object beforeReturnVal, Object returnVal);
    
    /**
     * 获取对象所有的接口，包括其父类的接口，已经父类的父类的。。。
     * @param clazz
     * @return
     */
    public static Class<?>[] getInterfaces(Class<?> clazz){
        Set<Class<?>> interfaces = new HashSet<Class<?>>();
        
        Class<?> superClazz = clazz;  
        while(superClazz != Object.class){
            interfaces.addAll(Arrays.asList(superClazz.getInterfaces()));
            superClazz = superClazz.getSuperclass();
        }
        
        Class<?>[] classes = new Class[interfaces.size()];
        return interfaces.toArray(classes);
    }
}

