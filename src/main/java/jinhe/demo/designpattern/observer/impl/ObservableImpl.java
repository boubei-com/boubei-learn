package jinhe.lt.designpattern.observer.impl;

import java.util.HashMap;
import java.util.Map;

import jinhe.lt.designpattern.observer.Observable;
import jinhe.lt.designpattern.observer.Observer;

/**
 * <p>
 * ObservableImpl.java
 * </p>
 * 
 * @author Jon.King 2006-4-23
 * 
 */
@SuppressWarnings("unchecked")
public class ObservableImpl extends Observable {

    // 封装被观察的数据
	private static  Map products= new HashMap(); // name/price 的产品列表

    private float discount; //产品折扣
    
    public void addProdunt(String name, Double price){
        products.put(name, price);
        this.setChanged();
        this.notifyObservers(new String("Products added!"));
    }
    
    public void modifyPrice(String name, Double newPrice){
        Double price = (Double) products.get(name);
        price = newPrice;
        this.setChanged();
        this.notifyObservers(new String("Product \"" + name + "\" 的price now have changed to " + price + "."));
    }

    public void setDiscount(float discount) {
        this.discount = discount;       
        this.setChanged();  //数据改变后，setChanged()必须被调用，否则notifyObservers()方法会不起作用
        this.notifyObservers(new String("discount changed to: " + this.discount + " !"));
    }

    /**
     * 将观察者注册到被观察的数据对象中
     * @param o
     */
    public void registerObserver(Observer  o){
        this.addObserver(o);
    }
}
