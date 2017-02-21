package jinhe.lt.designpattern.state.demo;

import java.awt.Color;

/**
 * <p>
 * State.java
 * </p>
 * 
 * @author Jon.King 2006-4-22
 * 
 */
public abstract class State {

    public abstract void handlePush(Context c);

    public abstract void handlePull(Context c);

    public abstract Color getcolor();

}
