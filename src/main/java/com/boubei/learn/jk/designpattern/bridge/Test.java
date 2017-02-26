package com.boubei.learn.jk.designpattern.bridge;


/**
 * <p>
 * Test.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 */
public class Test {
    public static void main(String[] args) {
        // 拿出牛奶
        //CoffeeImpSingleton coffeeImpSingleton = new CoffeeImpSingleton(new MilkCoffeeImp());
        CoffeeImpSingleton.getTheCoffeeImp().pourCoffeeImp();

        // 中杯加奶
        MediumCoffee mediumCoffee = new MediumCoffee();
        mediumCoffee.pourCoffee();

        // 大杯加奶
        SuperSizeCoffee superSizeCoffee = new SuperSizeCoffee();
        superSizeCoffee.pourCoffee();

    }

}
