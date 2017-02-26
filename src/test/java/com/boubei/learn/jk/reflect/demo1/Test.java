package com.boubei.learn.jk.reflect.demo1;

/**
 * <p>
 * Test.java
 * </p>
 * 
 * @author Jon.King 2006-4-19
 * 
 * 需调用代理产生器创建一个代理类来控制被代理的对象。 
 * 代理产生器的功能在JDK中也就是通过Proxy.newProxyInstance来动态创建一个代理类
 * 
 */
public class Test {
    
    public static void main(String[] arg) {
        CarCompany cc = new CarCompanyA();
        CarCompanyHandler handler = new CarCompanyHandler(cc);
        
        cc = (CarCompany) handler.createProxy();  // 产生一个新的代理类
        cc.makeCar();  //cc指向$Proxy0（代理产生器动态生成的类（Proxy.newProxyInstance产生的））
    }
}

/**
 代理产生器动态生成的类（Proxy.newProxyInstance产生的） 相信看了如下代码后就会很清楚动态代理的执行过程。

 public final class $Proxy0 extends Proxy implements CarCompany { 
    public final void makeCar(){ 
        try{           
            super.h.invoke(this, m3, null); //调用InvocationHandler（CarCompanyHandler）的invoke方法 
            return; 
        } 
        catch(Error _ex) { } 
        catch(Throwable throwable){ 
            throw new UndeclaredThrowableException(throwable); 
        } 
    } 
    private static Method m3; 
    static{ 
        try{ 
            m3 = Class.forName("com.home.CarCompany").getMethod("makeCar", new Class[0]); 
        }catch(NoSuchMethodException nosuchmethodexception){ 
            throw new NoSuchMethodError(nosuchmethodexception.getMessage()); 
        }catch(ClassNotFoundException classnotfoundexception){ 
            throw new NoClassDefFoundError(classnotfoundexception.getMessage()); 
        } 
    } 
    
    public $Proxy0(InvocationHandler invocationhandler){ 
        super(invocationhandler); 
    } 
 } */
