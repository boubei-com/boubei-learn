package jinhe.lt.reflect.proxydemo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * CarCompanyHandler.java
 * </p>
 * 
 * @author Jon.King 2006-4-19
 * 
 */
public class CarCompanyHandler implements InvocationHandler {
    
    public Object createProxy(){
        return Proxy.newProxyInstance(cc.getClass().getClassLoader(), cc.getClass().getInterfaces(), this);
    }

    CarCompany cc;

    public CarCompanyHandler(CarCompany a) {
        this.cc = a;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("makeCar")) {
            System.out.println("time1: " + System.currentTimeMillis());
            
            method.invoke(cc);
            
            System.out.println("time2: " + System.currentTimeMillis());
        }
        return null;
    }
}
