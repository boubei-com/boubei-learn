package jinhe.lt.reflect.proxydemo2;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * Proxy工厂
 * 
 * <p> ProxyFactory.java </p> 
 * 
 * @author Jon.King
 */

public class ProxyFactory {   
    
    /**
     * 根据目标对象和InvocationHandler生成动态代理类
     * @param target
     * @param handler
     * @return
     */
    public static Object createProxy(Object target, InvocationHandler handler){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(),   
                handler); 
    }
    
    /**
     * 如果只希望动态代理目标对象的其中一个或者几个接口，则需要将这些接口事先以Class[]形式传入
     * @param target
     * @param interfaces
     * @param handler
     * @return
     */
    public static Object createProxy(ClassLoader classLoader, Class<?>[] interfaces, InvocationHandler handler){
        return Proxy.newProxyInstance(classLoader, interfaces, handler);  
    }
    
    public static Object createProxy(Class<?>[] interfaces, InvocationHandler handler){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, handler);  
    }
    
    /**
     * 实现跟踪方法执行的功能,可以在方法执行前执行后记录一些信息
     * 
     * 动态代理类中并不知道代理的是那个具体对象，调用InvocationHandler的实现类才知道。
     * 被代理对象作为InvocationHandler实现类的一个属性
     * 
     * @author 金普俊  2006-8-14
     *
     */
    public static class TracingIH implements InvocationHandler{        
        private Object target; //被代理的对象
        private PrintWriter out;
        
        public TracingIH(Object obj, PrintWriter out){
            this.target = obj;
            this.out = out;
        }
        
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            out.println("proxy name: " + proxy.getClass());
            out.println("Fields     -------------------------------------------------------");
            Field[] fields = proxy.getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++){
                out.println(fields[i]);
            }
            out.println("Methods    -------------------------------------------------------");
            Method[] methods = proxy.getClass().getDeclaredMethods();
            for(int i = 0; i < methods.length; i++){
                out.println(methods[i]);
            }
            out.println("Constructors-------------------------------------------------------");
            Constructor<?>[] constructors = proxy.getClass().getDeclaredConstructors();
            for(int i = 0; i < constructors.length; i++){
                out.println(constructors[i]);
            }
            Object result = null;
            try{
                out.println("Before method invoke: " + method.getName() + "(...) called");
                
                result = method.invoke(target, args);  //拦截被代理对象的方法获取返回值(target:被代理的对象)
                
                out.println("After method invoke: " +  method.getName() + " returns");
            }catch(InvocationTargetException e){
                out.println(method.getName() + " throws "  + e.getCause());
                throw e.getCause();
            }              
            return result;
        }
    }
    
    public static Object createDogProxy(final Object target, final PrintWriter out){
        InvocationHandler handler = new TracingIH(target, out);
        return ProxyFactory.createProxy(target, handler);

    }   
    
}
