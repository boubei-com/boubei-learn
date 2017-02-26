package com.boubei.learn.jk.demo.fibonacci;

/**
 * <p>
 * Fibonacci.java
 * </p>
 * 
 * @author Jon.King 2006-10-12
 * 
 * 菲波那契(Fibonacci)数列的几种解法 
 */
interface Fibonacci {
    /**
     * 递归
     * @param index
     * @return
     */
    long recursive(int index);
    
    /**
     * 数组
     * @param index
     * @return
     */
    long fibArray(int index);
    
    /**
     * 循环
     * @param index
     * @return
     */
    long fib(int index);
}
