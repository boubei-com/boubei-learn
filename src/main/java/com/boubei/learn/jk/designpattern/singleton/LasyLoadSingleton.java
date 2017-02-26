package com.boubei.learn.jk.designpattern.singleton;

/**
 * lazy loading
 * 
 * @author  Jon.King 2007-2-28
 * 
 */
public class LasyLoadSingleton {
	
    static class SingletonHolder {
        static LasyLoadSingleton instance = new LasyLoadSingleton();
    }

    public static LasyLoadSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
