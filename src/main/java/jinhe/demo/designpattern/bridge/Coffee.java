package jinhe.lt.designpattern.bridge;

/**
 * <p>
 * Coffee.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 * biidge桥接模式,以一杯咖啡为例,子类实现类为四个： 中杯加奶、大杯加奶、 中杯不加奶、大杯不加奶。
 * 
 * 抽象 和 行为，其中抽象为：中杯和大杯； 行为为：加奶 不加奶（如加橙汁 加苹果汁
 * 
 * 抽象部分的接口
 * 
 */
public abstract class Coffee {
    CoffeeImp coffeeImp;

    public void setCoffeeImp() {
        this.coffeeImp = CoffeeImpSingleton.getTheCoffeeImp();

    }

    public CoffeeImp getCoffeeImp() {
        return this.coffeeImp;
    }

    public abstract void pourCoffee();
}
