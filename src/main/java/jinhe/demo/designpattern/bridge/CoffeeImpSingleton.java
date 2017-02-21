package jinhe.lt.designpattern.bridge;

/**
 * <p>
 * CoffeeImpSingleton.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 */
public class CoffeeImpSingleton {
    private static CoffeeImp coffeeImp;

    public CoffeeImpSingleton(CoffeeImp coffeeImpIn) {
        CoffeeImpSingleton.coffeeImp = coffeeImpIn;
    }

    public static CoffeeImp getTheCoffeeImp() {
        return coffeeImp;
    }
}
