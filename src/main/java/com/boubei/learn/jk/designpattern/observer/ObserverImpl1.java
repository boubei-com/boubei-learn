package com.boubei.learn.jk.designpattern.observer;


/** 
 * <p> ObserverImpl1.java </p> 
 * 
 * @author  Jon.King 2006-4-23
 *
 */
public class ObserverImpl1 implements Observer{
    
    String information = "ObserverImpl1 observering: ";

    public void update(Observable o, Object arg) {
        System.out.println(information + (String) arg);
    }

}

