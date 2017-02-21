package jinhe.lt.designpattern.singleton.lazyloading;

/**
 * <p>
 * Singleton.java
 * </p>
 * lazy loading
 * 
 * @author  Jon.King 2007-2-28
 * 
 */

public class Singleton {
    static class SingletonHolder {
        static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}
