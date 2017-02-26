package com.boubei.learn.jk.reflect;

import java.io.PrintWriter;

/** 
 * <p> DogFactory.java </p> 
 * 
 * @author Jon.King 2006-8-9
 *
 */
interface Dog{
    void init(String name, int size);
}

class LiziDog implements Dog{
    private String name;
    private int size;
    
    public void init(String name, int size){
        this.name = name;
        this.size = size;
        System.out.println("初始化了了对象： " + this);
    }
    
    public String toString(){
        return name + "_" + size;
    }
}

public class _DogFactory {

    private Class<?> dogClass;
    private boolean traceIsOn = false;
    
    public _DogFactory(String className, boolean trace){
        try{
            dogClass = Class.forName(className);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        this.traceIsOn = trace;
    }
    
    public Dog newInstance(String name, int size){
        try{
            Dog dog = (Dog)dogClass.newInstance();
            if(traceIsOn){
                dog = (Dog)_ProxyFactory.createDogProxy(dog, new PrintWriter(System.out, true));
                //等价于 dog = new $Proxy0(new TrancingIH(dog, new PrintWriter(System.out, true)));
            }
            dog.init(name, size);
            return dog;
        }catch(Exception e){
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args){
        _DogFactory factoty = new _DogFactory("jinhe.lt.reflect.proxydemo2.LiziDog", true);
        factoty.newInstance("Lizi", 22);
    }
}

/**
 * 代理产生器动态生成的类（Proxy.newProxyInstance产生的） 相信看了如下代码后就会很清楚动态代理的执行过程。
 * 动态代理类中并不知道代理的是那个具体对象，调用InvocationHandler的实现类才知道。被代理对象作为InvocationHandler实现类的一个属性

public final class $Proxy0 extends Proxy implements Dog { 
   
   private static Method m3;   //产生动态代理类时，被继承的所有接口（这里就Dog）的方法都像 m3 方法一样一一列出来，静态的初始化，以备代理类实现这些方法的时候用
   static{ 
       try{ 
           m3 = Dog.class.getMethod("init", new Class[]{String.class, Integer.TYPE}); 
       }catch(NoSuchMethodException nosuchmethodexception){ 
           throw new NoSuchMethodError(nosuchmethodexception.getMessage()); 
       }
   } 
   
   public $Proxy0(InvocationHandler invocationhandler){ 
       super(invocationhandler); 
   } 
   
   public final void init(String name, int size){ 
       try{           
           super.h.invoke(this, m3, new Object[]{name, new Integer(size)}); //super.h <===> Proxy.h , 调用InvocationHandler（CarCompanyHandler）的invoke方法 
           return; 
       } 
       catch(Error _ex) { } 
       catch(Throwable throwable){ 
           throw new UndeclaredThrowableException(throwable); 
       } 
   } 
} */
