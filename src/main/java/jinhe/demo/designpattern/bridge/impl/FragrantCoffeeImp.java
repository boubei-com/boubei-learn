package jinhe.lt.designpattern.bridge.impl;

import jinhe.lt.designpattern.bridge.CoffeeImp;

/**
 * <p>
 * FragrantCoffeeImp.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 */
public class FragrantCoffeeImp extends CoffeeImp {
    public FragrantCoffeeImp() {
    }

    public void pourCoffeeImp() {
        System.out.println("什么也没加,清香");
    }
}
