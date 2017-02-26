package com.boubei.learn.jk.designpattern.bridge;


/**
 * <p>
 * MilkCoffeeImp.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 */
public class MilkCoffeeImp extends CoffeeImp {
    public MilkCoffeeImp() {
    }

    public void pourCoffeeImp() {
        System.out.println("加了美味的牛奶");
    }
}
