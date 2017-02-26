package com.boubei.learn.jk.designpattern.observer;


/** 
 * <p> Test.java </p> 
 * 
 * @author  Jon.King 2006-4-23
 *
 */
public class Test {
    public static void main(String[] args){
        Observer o1 = new ObserverImpl1();
        Observer o2 = new ObserverImpl2();
       
        ObservableImpl subject = new ObservableImpl();
        
        subject.registerObserver(o1);
        subject.registerObserver(o2);
        
        System.out.println(subject.countObservers() + " observers are obersing ObservableImpl now!");
        
        subject.addProdunt("千岛湖", new Double(99999));
        subject.modifyPrice("千岛湖",new Double(8080808));
        subject.setDiscount((float) 0.8);
    }
}

