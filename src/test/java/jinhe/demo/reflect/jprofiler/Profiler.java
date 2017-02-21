package jinhe.lt.reflect.jprofiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <p>
 * Profiler.java
 * </p>
 * 计算对象方法的执行时间。 Decorator模式
 * 
 * @author Jon.King 2006-8-13
 * 
 */
public class Profiler{
  
    public static Object frofiler(final Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler(){

            private Logger log = Logger.getLogger(this.getClass());
            
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {     
                return invoke(method, args); 
            }

            private Object invoke(Method method, Object[] args) throws Throwable {
                try {
                    log.setLevel(Level.INFO);
                    log.info("方法( " + method.getName() + " )");
                    
                    long startTime = System.currentTimeMillis();
                    Object returnVal = method.invoke(object, args);
                    long endTime = System.currentTimeMillis();
                                        
                    log.info("方法( " + method.getName() + " )的执行时间为 ：" +  (endTime - startTime));
                    
                    return returnVal;
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        Fibonacci test = (Fibonacci) Profiler.frofiler(new FibonacciImpl());
        
        System.out.println(test.recursive(33)); //拦截递归调用得方法只能拦截住第一次
        System.out.println(test.fibArray(1000000));       
        System.out.println(test.fib(1000000));
    }
}
