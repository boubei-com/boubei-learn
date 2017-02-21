package jinhe.lt.designpattern.state.demo;

import java.awt.Color;

/** 
 * <p> RedState.java </p> 
 * 
 * @author Jon.King 2006-4-22
 *
 */
public class RedState extends State {

    public void handlePush(Context c) {
        // 根据push方法"如果是red状态的切换到blue"
        c.setState(new BlueState());
    }

    public void handlePull(Context c) {
        // 根据pull方法"如果是red状态的切换到green" ;
        c.setState(new GreenState());

    }

    public Color getcolor() {
        return Color.red;
    }

}

