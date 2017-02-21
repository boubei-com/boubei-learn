package jinhe.lt.designpattern.observer;

/** 
 * <p> Observer.java </p> 
 * 
 * @author  Jon.King 2006-4-23
 *
 * A class can implement the Observer interface when it
 * wants to be informed of changes in observable objects.
 */
public interface Observer {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     * 
     * notify 接收更新观察对象的信息
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the <code>notifyObservers</code>
     *                 method.
     */
    void update(Observable o, Object arg);
}

