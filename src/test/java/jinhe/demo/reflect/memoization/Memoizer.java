package jinhe.lt.reflect.memoization;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jinhe.lt.reflect.memoization.mixin.LockMixin;
import jinhe.lt.reflect.memoization.mixin.Lockable;

/**
 * <p>
 * Memoizer.java
 * </p>
 * 
 * 将object对象接口中的方法的返回值缓存起来。 Decorator模式
 * 
 * 加入了一个mixin功能
 * 
 * @author Jon.King 2006-8-13
 * 
 */
@SuppressWarnings("unchecked")
public class Memoizer{
    public static Object memoize(final Object object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        Class<?>[] temp = new Class[interfaces.length + 1];
        for(int i = 0; i < interfaces.length; i++){
            temp[i] = interfaces[i];
        }
        temp[temp.length - 1] = Lockable.class;  //将mixin的Lockable的接口也加进来

        return Proxy.newProxyInstance(object.getClass().getClassLoader(), temp, new InvocationHandler(){
            
			private Map caches = new HashMap();
            
            Object target = object;

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //如果调用方法是属于Lockable的，则将目标对象切换new LockMixin()；
                if(method.getDeclaringClass().equals(Lockable.class)){
                    target = new LockMixin();
                }
               
                if (method.getReturnType().equals(Void.TYPE)) {          
                    return invoking(target, method, args);   // Don't cache void methods                   
                } else {
                    Map cache = getCache(method);
                    List key = Arrays.asList(args);
                    Object value = cache.get(key);

                    if (value == null && !cache.containsKey(key)) {
                        value = invoking(target, method, args);
                        cache.put(key, value);
                    }
                    return value;
                }
            }

            private Object invoking(Object target, Method method, Object[] args) throws Throwable {
                try {
                    return method.invoke(target, args);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }

            private synchronized Map getCache(Method m) {
                Map cache = (Map) caches.get(m);
                if (cache == null) {
                    cache = Collections.synchronizedMap(new HashMap());
                    caches.put(m, cache);
                }
                return cache;
            }
        });
    }
    
    public static void main(String[] args){
        BinaryDigitsCalculator calculator = 
            (BinaryDigitsCalculator) Memoizer.memoize(new PiBinaryDigitsCalculator());  //Decorator模式
        byte returnVal = calculator.calculateBinaryDigit(100101);
        System.out.println(returnVal);
        
        Lockable lock = (Lockable) calculator;
        lock.lock();
        lock.unlock();
    }
}
