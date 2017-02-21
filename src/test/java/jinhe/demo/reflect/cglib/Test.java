package jinhe.lt.reflect.cglib;

import jinhe.lt.reflect.jprofiler.FibonacciImpl;

/** 
 * <p> Test.java </p> 
 * 
 * @author Jon.King 2006-10-24
 *
 */
public class Test {
    public static void main(String[] args){   
        FibonacciImpl  f = (FibonacciImpl)new Profiler().getProxy(FibonacciImpl.class);    
        f.fib(1000000);
        f.fibArray(1000000);
        f.recursive(12);
    }
}

