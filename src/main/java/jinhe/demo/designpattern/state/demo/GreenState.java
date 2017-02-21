package jinhe.lt.designpattern.state.demo;

import java.awt.Color;

/** 
 * <p> GreenState.java </p> 
 * 
 * @author Jon.King 2006-4-22
 *
 */
public class GreenState extends State {

    public void handlePush(Context c) {
        // 根据push方法"如果是green状态的切换到red"
        c.setState(new RedState());
    }

    public void handlePull(Context c) {
        // 根据pull方法"如果是green状态的切换到blue" ;
        c.setState(new BlueState());

    }

    public Color getcolor() {
        return Color.green;
    }
}

