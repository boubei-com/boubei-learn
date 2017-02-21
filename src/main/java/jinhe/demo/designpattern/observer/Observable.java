package jinhe.lt.designpattern.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Observable.java
 * </p>
 * 
 * @author Jon.King 2006-4-23
 * 
 */
@SuppressWarnings("unchecked")
public class Observable {
    private boolean changed = false;
    
	private Set obs;

    /** Construct an Observable with zero Observers. */
    public Observable() {
        obs = new HashSet();
    }

    /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set.
     * 
     * @param o
     *            an observer to be added.
     * @throws NullPointerException
     *             if the parameter o is null.
     */
    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * 
     * @param o
     *            the observer to be deleted.
     */
    public synchronized void deleteObserver(Observer o) {
        obs.remove(o);
    }

    /**
     * If this object has changed, as indicated(指出) by the <code>hasChanged</code>
     * method, then notify all of its observers and then call the
     * <code>clearChanged</code> method to indicate that this object has no
     * longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to: <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     * 
     * @see com.jinpj.design_pattern.observer.Observable#clearChanged()
     * @see com.jinpj.design_pattern.observer.Observable#hasChanged()
     * @see java.util.Observer#update(com.jinpj.design_pattern.observer.Observable, java.lang.Object)
     */
    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * If this object has changed, as indicated by the <code>hasChanged</code>
     * method, then notify all of its observers and then call the
     * <code>clearChanged</code> method to indicate that this object has no
     * longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and the <code>arg</code> argument.
     * 
     * 如果要给针对性的（不是所有）观察者发送通知，可以在Observable子类中重写该方法，或者
     * 写新的类似方法，结合Visitor模式
     * 
     * @param arg
     *            any object.
     * @see com.jinpj.design_pattern.observer.Observable#clearChanged()
     * @see com.jinpj.design_pattern.observer.Observable#hasChanged()
     * @see com.jinpj.design_pattern.observer.Observer#update(com.jinpj.design_pattern.observer.Observable, java.lang.Object)
     */
    public void notifyObservers(Object arg) {
        /*
         * a temporary array buffer, used as a snapshot（快照） of the state of current
         * Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /*
             * We don't want the Observer doing callbacks into arbitrary code
             * while holding its own Monitor. The code where we extract each
             * Observable from the Set and store the state of the Observer
             * needs synchronization, but notifying observers does not (should
             * not). The worst result of any potential race-condition here is
             * that:    1) a newly-added Observer will miss a notification in
             * progress 2) a recently unregistered Observer will be wrongly
             * notified when it doesn't care
             */
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((Observer) arrLocal[i]).update(this, arg);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    public synchronized void deleteObservers() {
        obs.removeAll(obs);
    }

    /**
     * Marks this <tt>Observable</tt> object as having been changed; the
     * <tt>hasChanged</tt> method will now return <tt>true</tt>.
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * Indicates that this object has no longer changed, or that it has already
     * notified all of its observers of its most recent change, so that the
     * <tt>hasChanged</tt> method will now return <tt>false</tt>. This
     * method is called automatically by the <code>notifyObservers</code>
     * methods.
     * 
     * @see com.jinpj.design_pattern.observer.Observable#notifyObservers()
     * @see com.jinpj.design_pattern.observer.Observable#notifyObservers(java.lang.Object)
     */
    protected synchronized void clearChanged() {
        changed = false;
    }

    /**
     * Tests if this object has changed.
     * 
     * @return <code>true</code> if and only if the <code>setChanged</code>
     *         method has been called more recently than the
     *         <code>clearChanged</code> method on this object;
     *         <code>false</code> otherwise.
     * @see com.jinpj.design_pattern.observer.Observable#clearChanged()
     * @see com.jinpj.design_pattern.observer.Observable#setChanged()
     */
    public synchronized boolean hasChanged() {
        return changed;
    }

    /**
     * Returns the number of observers of this <tt>Observable</tt> object.
     * 
     * @return the number of observers of this object.
     */
    public synchronized int countObservers() {
        return obs.size();
    }
}
