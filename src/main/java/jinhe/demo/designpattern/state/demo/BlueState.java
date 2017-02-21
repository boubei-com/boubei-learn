package jinhe.lt.designpattern.state.demo;

import java.awt.Color;

/**
 * <p>
 * BlueState.java
 * </p>
 * 
 * @author  Jon.King 2006-4-22
 * 
 */
public class BlueState extends State {

    public void handlePush(Context c) {
        // 根据push方法"如果是blue状态的切换到green"
        c.setState(new GreenState());
    }

    public void handlePull(Context c) {
        // 根据pull方法"如果是blue状态的切换到red" ;
        c.setState(new RedState());

    }

    public Color getcolor() {
        return Color.blue;
    }

}
