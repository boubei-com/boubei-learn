package jinhe.lt.designpattern.observer.impl;

import jinhe.lt.designpattern.observer.Observable;
import jinhe.lt.designpattern.observer.Observer;

/** 
 * <p> ObserverImpl2.java </p> 
 * 
 * @author Jon.King 2006-4-23
 *
 */
public class ObserverImpl2 implements Observer{
    
    String information = "ObserverImpl2 observering: ";

    public void update(Observable o, Object arg) {
        System.out.println(information + (String) arg);
    }

}

