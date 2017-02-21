package jinhe.lt.reflect.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <p>
 * Profiler.java
 * </p>
 * 
 * @author Jon.King 2006-10-24
 * 
 * 与Dynamic Proxy中的Proxy和InvocationHandler相对应，net.sf.cglib.proxy.Enhancer和MethodInterceptor
 * 在CGLib中负责完成(代理对象创建)和(方法截获处理), (产生的是目标类的子类)而不是通过接口来实现方法拦截的，
 * Enhancer主要是用于构造动态代理子类来实现拦截，
 * MethodInterceptor（扩展了Callback接口）主要用于实现around advice（AOP中的概念）：
 * 
 */
public class Profiler implements MethodInterceptor {
    private Logger log = Logger.getLogger(Profiler.class);

    public Object getProxy(Class<?> clazz) {
        Callback[] callbacks = new Callback[] { this,  NoOp.INSTANCE };
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(this.new CallbackFilterImpl());
        return enhancer.create();
    } 

    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.setLevel(Level.INFO);

        long startTime = System.currentTimeMillis();
        Object result = proxy.invokeSuper(o, args);
        long endTime = System.currentTimeMillis();
        log.info("方法( " + method.getName() + "( " + args[0] + " ))的执行时间为 ：" +  (endTime - startTime));
        
        return result;
    }
    
    private class CallbackFilterImpl implements CallbackFilter {        
        public int accept(Method method) {
            return method.getName().equals("recursive") ? 1 : 0 ;
        }
    }
}
